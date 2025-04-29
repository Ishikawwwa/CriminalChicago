DROP DATABASE IF EXISTS team16_projectdb CASCADE;

CREATE DATABASE team16_projectdb LOCATION "project/hive/warehouse";
USE team16_projectdb;

CREATE EXTERNAL TABLE crimes STORED as AVRO LOCATION 'project/warehouse/crimes'
TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/crimes.avsc');

SELECT * FROM crimes;

