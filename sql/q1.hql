-- -- Create a managed or external Hive Table qx_results to store the results of the query.
-- USE team16_projectdb;

-- DROP TABLE IF EXISTS q1_results;
-- CREATE EXTERNAL TABLE q1_results(
-- block_name STRING,
-- crimes_count INT)
-- ROW FORMAT DELIMITED
-- FIELDS TERMINATED BY ','
-- location 'project/hive/warehouse/q1'; 

-- -- Insert the command into the table
-- SET hive.resultset.use.unique.column.names = false;

-- INSERT INTO q1_results
-- SELECT 
-- block, COUNT(*) AS `sm`
-- FROM crimes_optimized
-- GROUP BY block
-- ORDER BY `sm` DESC
-- LIMIT 20;

-- SELECT * FROM q1_results;

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
FROM crimes_optimized
GROUP BY block
ORDER BY `sm` DESC
LIMIT 20;

INSERT OVERWRITE DIRECTORY 'project/output/q1' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q1_results;