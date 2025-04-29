USE team16_projectdb;

DROP TABLE IF EXISTS q6_results;

CREATE EXTERNAL TABLE q6_results(
primary_type STRING,
avg_beat FLOAT,
avg_district FLOAT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q6';

-- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q6_results
SELECT 
    primary_type,
    AVG(beat) AS avg_beat,
    AVG(district) AS avg_district
FROM crimes_optimized
GROUP BY primary_type;


SELECT * FROM q6_results;