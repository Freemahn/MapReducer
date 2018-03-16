## How to build
Run `gradle fatJar` from project root  
in *build/libs* you will find average-count-1.0-SNAPSHOT.jar

## How to run
Considering hadoop installed in %HADOOP_HOME%

`%HADOOP_HOME%/bin/hadoop jar path/to/jar path/to/input path/to/output`  
Example  
`hadoop jar build/libs/average-count-1.0-SNAPSHOT.jar user/problem1/input user/problem1/output`  
Or run `run.sh` with default settings  
## How to view results
`hadoop fs -cat user/problem1/output`