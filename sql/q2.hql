USE team16_projectdb;

DROP TABLE IF EXISTS q2_results;
CREATE EXTERNAL TABLE q2_results(
category STRING,
total INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
location 'project/hive/warehouse/q2'; 

-- -- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q2_results
SELECT 
  'Arrested' AS category, COUNT(*) 
FROM crimes WHERE arrest = true
UNION ALL
SELECT 
  'Not Arrested', COUNT(*) 
FROM crimes WHERE arrest = false;


SELECT * FROM q2_results;