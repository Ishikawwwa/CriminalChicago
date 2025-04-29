USE team16_projectdb;

DROP TABLE IF EXISTS q2_results;
CREATE EXTERNAL TABLE q2_results(
year INT,
month INT,
total_crimes INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
location 'project/hive/warehouse/q2'; 

-- -- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q2_results
SELECT 
    year,
    month,
    COUNT(*) AS total_crimes
FROM crimes_optimized 
GROUP BY year, month
ORDER BY year, month;

SELECT * FROM q1_results;