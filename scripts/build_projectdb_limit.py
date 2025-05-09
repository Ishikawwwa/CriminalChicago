import psycopg2 as psql
import os
import glob
import csv
import io
import time
import psutil
import signal
from contextlib import contextmanager

class TimeoutException(Exception):
    pass

@contextmanager
def time_limit(seconds):
    def signal_handler(signum, frame):
        raise TimeoutException("Timed out!")
    signal.signal(signal.SIGALRM, signal_handler)
    signal.alarm(seconds)
    try:
        yield
    finally:
        signal.alarm(0)

def check_system_resources(min_memory=100, min_cpu=10):
    mem = psutil.virtual_memory()
    cpu = psutil.cpu_percent(interval=1)
    
    # Convert MB to bytes
    min_memory_bytes = min_memory * 1024 * 1024
    
    if mem.available < min_memory_bytes:
        print(f"Memory low - available: {mem.available/(1024*1024):.2f}MB (needs {min_memory}MB)")
        return False
    
    if cpu > (100 - min_cpu):
        print(f"CPU busy - usage: {cpu}% (needs <{100-min_cpu}% available)")
        return False
    
    return True

def wait_for_resources(min_memory=100, min_cpu=10, max_wait=300):
    start_time = time.time()
    while True:
        if check_system_resources(min_memory, min_cpu):
            return True
        
        if time.time() - start_time > max_wait:
            raise Exception("Timeout waiting for resources")
        
        print("Waiting for resources...")
        time.sleep(10)

def clean_csv_chunk(reader, header, chunk_size=10000):
    chunk = []
    for _ in range(chunk_size):
        try:
            row = next(reader)
            if len(row) != len(header) or row[1] == 'ID':
                continue
                
            for i in [1, 11, 12]:
                if i < len(row) and row[i] and row[i].endswith('.0'):
                    row[i] = row[i][:-2]
            chunk.append(row)
        except StopIteration:
            break
    
    if not chunk:
        return None
        
    output = io.StringIO()
    writer = csv.writer(output)
    writer.writerows(chunk)
    output.seek(0)
    return output

def process_file_with_retries(crime_file, cur, import_command, max_retries=3):
    with open(crime_file, 'r') as f:
        reader = csv.reader(f)
        header = next(reader)
        
        while True:
            try:
                wait_for_resources()
                
                # Process in chunks with timeout
                with time_limit(60):  # 60 second timeout per chunk
                    chunk = clean_csv_chunk(reader, header, 5000)
                    if not chunk:
                        break
                    
                    cur.copy_expert(import_command, chunk)
                    return True
                    
            except (TimeoutException, psql.OperationalError) as e:
                print(f"Error processing chunk: {str(e)} - retrying...")
                time.sleep(10)
                continue
            except Exception as e:
                print(f"Fatal error processing file: {str(e)}")
                return False

def main():
    with open(os.path.join("secrets", ".psql.pass"), "r") as f:
        password = f.read().rstrip()

    conn_string = f"host=hadoop-04.uni.innopolis.ru port=5432 user=team16 dbname=team16_projectdb password={password}"

    try:
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
                    
                    cur.execute("SELECT COUNT(*) FROM crimes")
                    before_count = cur.fetchone()[0]
                    
                    success = process_file_with_retries(crime_file, cur, import_command)
                    if not success:
                        continue
                        
                    conn.commit()
                    
                    cur.execute("SELECT COUNT(*) FROM crimes")
                    after_count = cur.fetchone()[0]
                    
                    with open(crime_file, 'r') as f:
                        input_count = sum(1 for _ in csv.reader(f)) - 1  # minus header
                    duplicates = input_count - (after_count - before_count)
                    total_duplicates += duplicates
                    print(f"Processed {input_count} rows ({duplicates} duplicates)")
                    
    except Exception as e:
        print(f"Fatal error: {str(e)}")
        return 1
        
    print(f"Import complete. Total duplicates: {total_duplicates}")
    return 0

if __name__ == "__main__":
    main()
