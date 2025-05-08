#!/bin/bash

mv *.avsc ~/project/CriminalChicago/output/

hdfs dfs -rm -r project/warehouse/avsc
hdfs dfs -mkdir -p project/warehouse/avsc
hdfs dfs -put ~/project/CriminalChicago/output/crimes.avsc project/warehouse/avsc/crimes.avsc

password=$(head -n 1 ~/project/CriminalChicago/secrets/.hive.pass)

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/odb.hql > ~/project/CriminalChicago/output/hive_results.txt


beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/q1.hql --hiveconf hive.resultset.use.unique.column.names=false > ~/project/CriminalChicago/output/q1.csv
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/q2.hql --hiveconf hive.resultset.use.unique.column.names=false > ~/project/CriminalChicago/output/q2.csv 
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/q3.hql --hiveconf hive.resultset.use.unique.column.names=false > ~/project/CriminalChicago/output/q3.csv
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/q4.hql --hiveconf hive.resultset.use.unique.column.names=false > ~/project/CriminalChicago/output/q4.csv
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/q5.hql --hiveconf hive.resultset.use.unique.column.names=false > ~/project/CriminalChicago/output/q5.csv
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/q6.hql --hiveconf hive.resultset.use.unique.column.names=false > ~/project/CriminalChicago/output/q6.csv
