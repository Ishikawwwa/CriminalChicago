USE team16_projectdb;

DROP TABLE IF EXISTS q4_results;

CREATE EXTERNAL TABLE q4_results(
primary_type STRING,
domestic_crimes INT,
non_domestic_crimes INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q4';

-- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q4_results
SELECT primary_type,
       SUM(CASE WHEN domestic IS TRUE THEN 1 ELSE 0 END) AS domestic_crimes,
       SUM(CASE WHEN domestic IS FALSE THEN 1 ELSE 0 END) AS non_domestic_crimes
FROM crimes
GROUP BY primary_type;

SELECT * FROM q4_results
ORDER BY domestic_crimes DESC;
