USE team16_projectdb;

DROP TABLE IF EXISTS model1_predictions;

CREATE EXTERNAL TABLE model1_predictions (
  label DOUBLE,
  prediction DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION 'project/output/model1_predictions';