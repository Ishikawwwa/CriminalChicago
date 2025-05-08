USE team16_projectdb;

DROP TABLE IF EXISTS q1_results;
CREATE EXTERNAL TABLE q1_results(
block_name STRING,
crimes_count INT)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
location 'project/hive/warehouse/q1'; 

-- -- Insert the command into the table
SET hive.resultset.use.unique.column.names = false;

INSERT INTO q1_results
SELECT 
block, COUNT(*) AS `sm`
FROM crimes
GROUP BY block
ORDER BY `sm` DESC
LIMIT 20;

SELECT * FROM q1_results;