from pyspark.sql import SparkSession
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.sql import Row
from pyspark.sql.types import StructType, StructField, StringType, DoubleType

def run(command):
    import os
    return os.popen(command).read()

spark = SparkSession.builder \
    .appName("Model Evaluation Comparison") \
    .master("yarn") \
    .getOrCreate()

pred1 = spark.read.csv("project/output/model1_predictions.csv", header=True, inferSchema=True)
pred2 = spark.read.csv("project/output/model2_predictions.csv", header=True, inferSchema=True)

evaluator = RegressionEvaluator(
    labelCol="label", 
    predictionCol="prediction"
)

metrics = [
    Row(
        Model="Linear Regression",
        RMSE=evaluator.setMetricName("rmse").evaluate(pred1),
        R2=evaluator.setMetricName("r2").evaluate(pred1)
    ),
    Row(
        Model="GBT Regression",
        RMSE=evaluator.setMetricName("rmse").evaluate(pred2),
        R2=evaluator.setMetricName("r2").evaluate(pred2)
    )
]

schema = StructType([
    StructField("Model", StringType()),
    StructField("RMSE", DoubleType()),
    StructField("R2", DoubleType())
])

eval_df = spark.createDataFrame(metrics, schema)

eval_df.coalesce(1) \
    .write \
    .mode("overwrite") \
    .format("csv") \
    .option("sep", ",") \
    .option("header", "true") \
    .save("project/output/evaluation")

run("hdfs dfs -cat project/output/evaluation/*.csv > ../output/evaluation.csv")

print("Evaluation results:")
eval_df.show()

# spark.stop()