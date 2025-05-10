#!/bin/bash

export HADOOP_CONF_DIR=/etc/hadoop/conf
export YARN_CONF_DIR=/usr/local/hadoop/etc/hadoop

spark-submit --master yarn preprocess.py

spark-submit --master yarn train_lr.py

spark-submit --master yarn train_gbt.py

spark-submit --master yarn compare.py

pylint *.py > ../output/pylint.txt || true