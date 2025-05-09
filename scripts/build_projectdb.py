import psycopg2 as psql
from pprint import pprint
import os
import glob
import csv
import io
import tempfile

def clean_csv_file(input_path, output_path):
    with open(input_path, 'r', newline='') as infile, \
         open(output_path, 'w', newline='') as outfile:
        reader = csv.reader(infile)
        writer = csv.writer(outfile)
        
        header = next(reader)
        writer.writerow(header)
        
        for row in reader:
            if len(row) != len(header) or (row and row[0] == 'ID'):
                continue
                
            indices_to_clean = [1, 11, 12]
            
            for col_idx_actual in indices_to_clean:
                if col_idx_actual < len(row) and row[col_idx_actual] and isinstance(row[col_idx_actual], str) and row[col_idx_actual].endswith('.0'):
                    row[col_idx_actual] = row[col_idx_actual][:-2]
                    
            writer.writerow(row)

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
            
            with tempfile.NamedTemporaryFile(mode='w+', delete=False, newline='') as temp_cleaned_file:
                temp_cleaned_path = temp_cleaned_file.name
            
            clean_csv_file(crime_file, temp_cleaned_path)
            
            cur.execute("SELECT COUNT(*) FROM crimes")
            before_count = cur.fetchone()[0]
            
            with open(temp_cleaned_path, 'r') as f_cleaned:
                cur.copy_expert(import_command, f_cleaned)
            conn.commit()
            
            cur.execute("SELECT COUNT(*) FROM crimes")
            after_count = cur.fetchone()[0]
            
            input_count = 0
            with open(temp_cleaned_path, 'r', newline='') as f_cleaned_for_count:
                reader = csv.reader(f_cleaned_for_count)
                next(reader)
                input_count = sum(1 for _ in reader)
                
            duplicates = input_count - (after_count - before_count)
            total_duplicates += duplicates
            
            print(f"  Added {after_count - before_count} records, skipped {duplicates} duplicates")
            
            os.remove(temp_cleaned_path)
    
    print(f"\nTotal duplicates skipped across all files: {total_duplicates}")

    print("\nRunning test...")
    with open(os.path.join("sql", "test_database.sql"), 'r') as f:
        cur.execute(f.read())
        pprint(cur.fetchall())
    
    conn.commit()
    print("All operations completed successfully")

