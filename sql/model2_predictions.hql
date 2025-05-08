USE team16_projectdb;

DROP TABLE IF EXISTS model2_predictions;

CREATE EXTERNAL TABLE model2_predictions (
  label DOUBLE,
  prediction DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION 'project/output/model2_predictions';