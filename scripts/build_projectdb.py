import psycopg2 as psql
from pprint import pprint
import os
import glob
import csv
import io

def clean_csv_file(input_path):
    cleaned_rows = []
    with open(input_path, 'r') as f:
        reader = csv.reader(f)
        header = next(reader)
        
        for row in reader:
            if len(row) != len(header) or row[1] == 'ID':
                continue
                
            for i in [1, 11, 12]:  # Positions of id, beat, district
                if i < len(row) and row[i] and row[i].endswith('.0'):
                    row[i] = row[i][:-2]
                    
            cleaned_rows.append(row)
    
    output = io.StringIO()
    writer = csv.writer(output)
    writer.writerow(header)
    writer.writerows(cleaned_rows)
    output.seek(0)
    return output

with open(os.path.join("secrets", ".psql.pass"), "r") as f:
    password = f.read().rstrip()

conn_string = f"host=hadoop-04.uni.innopolis.ru port=5432 user=team16 dbname=team16_projectdb password={password}"

with psql.connect(conn_string) as conn:
    conn.autocommit = False
    cur = conn.cursor()
    
    cur.execute("SET datestyle = 'ISO, MDY'")
    
    print("Creating tables...")
    with open(os.path.join("sql", "create_tables.sql")) as f:
        cur.execute(f.read())
    
    crime_files = sorted(glob.glob(os.path.join("data", "Chicago_Crimes_*.csv")))
    if not crime_files:
        raise FileNotFoundError("No crime data files found")
    
    print("Importing data...")
    total_duplicates = 0
    
    with open(os.path.join("sql", "import_data.sql")) as f:
        import_command = f.read()
        
        for crime_file in crime_files:
            print(f"Processing {os.path.basename(crime_file)}...")
            cleaned_data = clean_csv_file(crime_file)
            
            cur.execute("SELECT COUNT(*) FROM crimes")
            before_count = cur.fetchone()[0]
            
            cur.copy_expert(import_command, cleaned_data)
            conn.commit()
            
            cur.execute("SELECT COUNT(*) FROM crimes")
            after_count = cur.fetchone()[0]
            
            cleaned_data.seek(0)
            reader = csv.reader(cleaned_data)
            next(reader)  # Skip header
            input_count = sum(1 for _ in reader)
            duplicates = input_count - (after_count - before_count)
            total_duplicates += duplicates
            
            print(f"  Added {after_count - before_count} records, skipped {duplicates} duplicates")
    

    print("\nRunning test...")
    with open(os.path.join("sql", "test_database.sql"), 'r') as f:
        cur.execute(f.read())
        pprint(cur.fetchall())
    
    conn.commit()
    print("All operations completed successfully")
