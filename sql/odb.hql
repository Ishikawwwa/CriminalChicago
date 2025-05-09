DROP DATABASE IF EXISTS team16_projectdb CASCADE;
CREATE DATABASE team16_projectdb LOCATION "project/hive/warehouse";
USE team16_projectdb;

CREATE EXTERNAL TABLE crimes STORED as AVRO LOCATION 'project/warehouse/crimes'
TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/crimes.avsc');

-- SELECT * FROM crimes; -- Commented out to prevent GC overhead on client

CREATE EXTERNAL TABLE crimes_optimized (
  id BIGINT,
  block STRING,
  primary_type STRING,
  location_description STRING,
  arrest BOOLEAN,
  domestic BOOLEAN,
  beat INT,
  district INT,
  ward DOUBLE,
  community_area DOUBLE,
  fbi_code STRING,
  x_coordinate DOUBLE,
  y_coordinate DOUBLE
)
PARTITIONED BY (year INT, month INT, day INT)
STORED AS AVRO
LOCATION 'project/hive/warehouse/crimes_optimized'
TBLPROPERTIES (
  'avro.schema.literal'='{
    "type":"record",
    "name":"crimes_optimized",
    "fields":[
      {"name":"id","type":["null","int"]},
      {"name":"block","type":["null","string"]},
      {"name":"primary_type","type":["null","string"]},
      {"name":"location_description","type":["null","string"]},
      {"name":"arrest","type":["null","boolean"]},
      {"name":"domestic","type":["null","boolean"]},
      {"name":"beat","type":["null","int"]},
      {"name":"district","type":["null","int"]},
      {"name":"ward","type":["null","double"]},
      {"name":"community_area","type":["null","double"]},
      {"name":"fbi_code","type":["null","string"]},
      {"name":"x_coordinate","type":["null","double"]},
      {"name":"y_coordinate","type":["null","double"]}
    ]
  }'
);

SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
SET hive.exec.max.dynamic.partitions.pernode=1000;
SET hive.exec.max.dynamic.partitions=10000;

INSERT OVERWRITE TABLE crimes_optimized PARTITION (year, month, day)
SELECT 
  id,
  block,
  primary_type,
  location_description,
  arrest,
  domestic,
  beat,
  district,
  ward,
  community_area,
  fbi_code,
  x_coordinate,
  y_coordinate,
  YEAR(FROM_UNIXTIME(CAST(`date`/1000 AS BIGINT))) AS year,
  MONTH(FROM_UNIXTIME(CAST(`date`/1000 AS BIGINT))) AS month,
  DAY(FROM_UNIXTIME(CAST(`date`/1000 AS BIGINT))) AS day
FROM crimes;


