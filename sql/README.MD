odb.hql - optimized Hive tables creation

<<<<<<< HEAD

### Stage 1 and 2 queries: database and tables creation 
db.hql
create_tables.hql
import_data.hql
test_database.sql
odb.hql
import_data.sql

#### Data Insights

Queries q1 to q6 are HiveQL queries that will be used to create the charts on the dashboard for data insights

### Results Queries

create tables for the model results so that we can display the tables on the dashboard.
- evaluation.hql
- model1_predictions.hql
- model2_predictions.hql
=======
db.hql - simple tables with no optimizations, used for scenarios when running odb.hql could not be completed because of high load on cluster resources

create_tables.sql - sql queries for creating the postgre tables according to the data schema

import_data.sql - sql queries for importing the data into the created tables

test_database.sql - sql queries for simple retrieve the first rows from the database
>>>>>>> 783940117157b5a0e10e2b5c032e364c21bb5de4
