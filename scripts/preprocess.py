from pyspark.sql import SparkSession
from pyspark.sql.functions import col, lead, lag, unix_timestamp
from pyspark.ml.feature import StringIndexer, VectorAssembler
from pyspark.sql import functions as F
from pyspark.sql.window import Window

team = 16
warehouse = 'project/hive/warehouse'

# Initialize Spark Session
spark = SparkSession.builder \
    .appName(f"{team} - spark ML") \
    .master("yarn") \
    .config("hive.metastore.uris", "thrift://hadoop-02.uni.innopolis.ru:9883") \
    .config("spark.sql.warehouse.dir", warehouse) \
    .config("spark.sql.avro.compression.codec", "snappy") \
    .enableHiveSupport() \
    .getOrCreate()

# Load and clean data
sdf = spark.table("team16_projectdb.crimes").dropna()

# Create timestamp column
sdf = sdf.withColumn("ts", F.to_timestamp(F.from_unixtime(col("date"))))

# Window specification MUST be defined before use
window_spec = Window.partitionBy("district").orderBy("ts")

# Calculate time until next crime (label)
sdf = sdf.withColumn("ts_next", lead("ts", 1).over(window_spec)) \
         .withColumn("label", 
                    F.when(col("ts_next").isNotNull(), 
                          F.unix_timestamp("ts_next") - F.unix_timestamp("ts")) \
                    .otherwise(None))

# Encode categorical features
for col_name, output_col in [("primary_type", "primary_type_enc"),
                            ("fbi_code", "fbi_code_enc"),
                            ("district", "district_enc")]:
    indexer = StringIndexer(inputCol=col_name, outputCol=output_col)
    sdf = indexer.fit(sdf).transform(sdf)

# Define base features
base_features = [
    'primary_type_enc', 
    'fbi_code_enc', 
    'arrest', 
    'domestic', 
    'beat', 
    'ward', 
    'community_area'
]

# Filter out null labels
sdf = sdf.filter(col("label").isNotNull())

# Create lag features
for lag_num in [1, 2, 3]:
    window_spec_lag = Window.partitionBy("district_enc").orderBy("ts")
    for feature in base_features:
        sdf = sdf.withColumn(f"{feature}_prev{lag_num}", 
                           lag(col(feature), lag_num).over(window_spec_lag))
    # Filter nulls after creating each set of lag features
    for feature in base_features:
        sdf = sdf.filter(col(f"{feature}_prev{lag_num}").isNotNull())

# Prepare feature vector
feature_columns = base_features + ['district_enc'] + \
                 [f'{col}_prev{lag_num}' for col in base_features for lag_num in [1, 2, 3]]

assembler = VectorAssembler(inputCols=feature_columns, outputCol="features")
final_data = assembler.transform(sdf)

train_data, test_data = final_data.randomSplit([0.8, 0.2], seed=42)

train_data.select("features", "label") \
    .coalesce(1) \
    .write \
    .mode("overwrite") \
    .format("json") \
    .save("project/data/train")

test_data.select("features", "label") \
    .coalesce(1) \
    .write \
    .mode("overwrite") \
    .format("json") \
    .save("project/data/test")

def run(command):
    import os
    return os.popen(command).read()
run("hdfs dfs -cat project/data/train/*.json > ../data/train.json")

run("hdfs dfs -cat project/data/test/*.json > ../data/test.json")
# spark.stop()
