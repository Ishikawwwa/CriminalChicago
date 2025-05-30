#!/bin/bash

source ~/project/crimes_venv/bin/activate
source ~/project/CriminalChicago/scripts/data_collection.sh
source ~/project/CriminalChicago/scripts/data_storage.sh
password=$(head -n 1 ~/project/CriminalChicago/secrets/.psql.pass)
hdfs dfs -rm -r -f /user/team16/project/warehouse/crimes
sqoop import \
    -D mapreduce.map.memory.mb=2048 \
    -D mapreduce.map.java.opts="-Xmx896m -XX:MaxMetaspaceSize=256m" \
    --connect jdbc:postgresql://hadoop-04.uni.innopolis.ru/team16_projectdb \
    --username team16 \
    --password $password \
    --table crimes \
    --compression-codec=snappy \
    --compress \
    --as-avrodatafile \
    --warehouse-dir /user/team16/project/warehouse \
    --m 1

