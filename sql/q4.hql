USE team16_projectdb;

DROP TABLE IF EXISTS q4_results;

CREATE EXTERNAL TABLE q4_results(
year INT,
month INT,
domestic_crimes INT,
non_domestic_crimes INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q4';

-- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q4_results
SELECT 
    year,
    month,
    SUM(CASE WHEN domestic is TRUE THEN 1 ELSE 0 END) AS domestic_crimes,
    SUM(CASE WHEN domestic is FALSE THEN 1 ELSE 0 END) AS non_domestic_crimes
FROM crimes_optimized
GROUP BY year, month;

SELECT * FROM q4_results
ORDER BY year, month;