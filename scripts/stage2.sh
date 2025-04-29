#!/bin/bash

hdfs dfs -rm project/warehouse/avsc/crimes.avsc
hdfs dfs -put crimes.avsc project/warehouse/avsc

mv *.avsc ~/project/CriminalChicago/output/

password=$(head -n 1 ~/project/CriminalChicago/secrets/.hive.pass)

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/odb.hql > ~/project/CriminalChicago/output/hive_results.txt
