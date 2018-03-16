## How to build
Run `gradle fatJar` from project root  
in *build/libs* you will find statistic-count.jar

## How to run
Considering hadoop installed in %HADOOP_HOME%

`%HADOOP_HOME%/bin/hadoop jar path/to/jar path/to/input path/to/output`  
Example  
`hadoop jar build/libs/statistic-count.jar user/problem2/input user/problem2/output`  
Or run `run.sh` with default settings  
## How to view results
`hadoop fs -cat user/problem2/output`