USE team16_projectdb;

DROP TABLE IF EXISTS q5_results;

CREATE EXTERNAL TABLE q5_results(
fbi_code STRING,
crime_count INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q5';

-- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q5_results
SELECT 
    fbi_code,
    COUNT(*) AS crime_count
FROM crimes
GROUP BY fbi_code;

SELECT * FROM q5_results
ORDER BY crime_count DESC;