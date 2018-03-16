## How to build
Run `gradle fatJar` from project root  
in *build/libs* you will find word-count.jar

## How to run
Considering hadoop installed in %HADOOP_HOME%

`%HADOOP_HOME%/bin/hadoop jar path/to/jar path/to/input path/to/output`  
Example  
`hadoop jar build/libs/word-count.jar user/problem3/input user/problem3/output`  
Or run `run.sh` with default settings  
## How to view results
`hadoop fs -cat user/problem3/output`