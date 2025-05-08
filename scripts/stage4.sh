#!/bin/bash


password=$(head -n 1 ~/project/CriminalChicago/secrets/.hive.pass)

hdfs dfs -mkdir -p project/output/evaluation/
hdfs dfs -mkdir -p project/output/model1_predictions/
hdfs dfs -mkdir -p project/output/model2_predictions/

hadoop fs -put -f ~/project/CriminalChicago/output/evaluation.csv project/output/evaluation/
hadoop fs -put -f ~/project/CriminalChicago/output/model1_predictions.csv project/output/model1_predictions/
hadoop fs -put -f ~/project/CriminalChicago/output/model2_predictions.csv project/output/model2_predictions/

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/evaluation.hql
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/model1_predictions.hql
beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team16 -p $password -f ~/project/CriminalChicago/sql/model2_predictions.hql