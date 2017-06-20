# CSE291-Graph-Database
Course project of UCSD CSE-291K Management of Large Scale Graph Database

## Dependencies

The project depends on following libraries:
```
com.sangupta:bloomfilter:0.9.0
mysql:mysql-connector-java:5.1.42
org.json:json:20170516
org.apache.commons:commons-csv:1.4
antlr-complete-4.6
```

To run this project, you must first resolve these dependencies. 

## Configuration:

The project is based on MySQL 5.7.15 Homebrew. To import data, you must first provide the username (default "root"), and password (default ""). Then create a database named "graphdb". The original 3.4GB csv data file should be placed in the root folder of this project, and renamed as `data.csv`.

Sample Cypher query string is in `query.txt` under project root folder. You can change the query string, but keep it valid. 

## Run project

Simple compile the project, and run the main function in `Main.java`. It generally takes 3-4 hours to import the whole file, and about 10 minutes to set up indices. 
