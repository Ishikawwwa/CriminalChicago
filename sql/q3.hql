USE team16_projectdb;

DROP TABLE IF EXISTS q3_results;

CREATE EXTERNAL TABLE q3_results(
primary_type STRING,
crime_count INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q3';

-- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q3_results
SELECT 
    primary_type,
    COUNT(*) AS crime_count
FROM crimes_optimized
GROUP BY primary_type;

SELECT * FROM q3_results
ORDER BY crime_count DESC;