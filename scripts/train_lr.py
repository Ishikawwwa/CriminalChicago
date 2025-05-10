from pyspark.ml.regression import LinearRegression
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.sql import SparkSession
from pyspark.sql.functions import col, lit
from pyspark.ml.linalg import Vectors, VectorUDT, SparseVector
from pyspark.sql.types import StructType, StructField, ArrayType, DoubleType, LongType
import pyspark.sql.functions as F

# Initialize Spark Session
spark = SparkSession.builder \
    .appName("Crime Prediction - Linear Regression Tuning") \
    .master("yarn") \
    .getOrCreate()

# Custom function to convert struct to vector with null handling
def struct_to_vector(struct_col):
    if struct_col is None or struct_col.size is None:
        return None
    try:
        return SparseVector(
            int(struct_col.size),
            [int(i) for i in struct_col.indices],
            [float(v) for v in struct_col.values]
        )
    except:
        return None

# Register UDF
struct_to_vector_udf = F.udf(struct_to_vector, VectorUDT())

# Define schema for input data
schema = StructType([
    StructField("features", StructType([
        StructField("indices", ArrayType(LongType())),
        StructField("size", LongType()),
        StructField("type", LongType()),
        StructField("values", ArrayType(DoubleType()))
    ])),
    StructField("label", LongType())
])

# Load prepared data
train_data = spark.read.schema(schema).json("project/data/train")
test_data = spark.read.schema(schema).json("project/data/test")

# Convert struct features to vector and label to double with null filtering
train_data = train_data.withColumn("features_vec", struct_to_vector_udf(col("features"))) \
                      .withColumn("label", col("label").cast("double")) \
                      .filter(col("features_vec").isNotNull())

test_data = test_data.withColumn("features_vec", struct_to_vector_udf(col("features"))) \
                    .withColumn("label", col("label").cast("double")) \
                    .filter(col("features_vec").isNotNull())



print("Data sample after conversion:")
train_data.select("features_vec", "label").show(5, truncate=False)
# Initialize Linear Regression model with correct feature column
lr = LinearRegression(featuresCol="features_vec", labelCol="label")

# Create evaluator
evaluator = RegressionEvaluator(
    labelCol="label", 
    predictionCol="prediction", 
    metricName="rmse"
)

# Parameter grid for tuning
lr_param_grid = (ParamGridBuilder()
    .addGrid(lr.regParam, [0.01, 0.1])  # Regularization parameter
    .addGrid(lr.elasticNetParam, [0.0, 0.5, 1.0])  # Elastic net mixing parameter
    .build())

# Create 3-fold cross validator
lr_cv = CrossValidator(estimator=lr,
                     estimatorParamMaps=lr_param_grid,
                     evaluator=evaluator,
                     numFolds=3,
                     parallelism=4)

# Train model with hyperparameter tuning
print("Tuning Linear Regression...")
lr_model = lr_cv.fit(train_data)
best_lr = lr_model.bestModel

# Evaluate model
print("\n=== Best Model Parameters ===")
print("Linear Regression:")
print(f"  regParam: {best_lr.getRegParam()}")
print(f"  elasticNetParam: {best_lr.getElasticNetParam()}")
print(f"  maxIter: {best_lr.getMaxIter()}")

print("\n=== Model Evaluation ===")
predictions = best_lr.transform(test_data)
rmse = evaluator.evaluate(predictions)
print(f"Linear Regression - Root Mean Squared Error (RMSE): {rmse:.2f} seconds")
predictions.select("label", "prediction").show(5)

model_dir = "project/models/"
best_lr.write().overwrite().save(model_dir + "model1")

predictions.select("label", "prediction")\
    .coalesce(1)\
    .write\
    .mode("overwrite")\
    .format("csv")\
    .option("sep", ",")\
    .option("header","true")\
    .save("project/output/model1_predictions.csv")

def run(command):
    import os
    return os.popen(command).read()

run("hdfs dfs -get project/models/model1 ../models/model1")
run("hdfs dfs -cat project/output/model1_predictions.csv/*.csv > ../output/model1_predictions.csv")

 