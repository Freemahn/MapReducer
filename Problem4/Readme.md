## How to build
Run `gradle fatJar` from project root  
in *build/libs* you will find matrix-multiplication.jar

## How to run
Considering hadoop installed in %HADOOP_HOME%

`%HADOOP_HOME%/bin/hadoop jar path/to/jar path/to/input path/to/output`  
Example  
`hadoop jar build/libs/matrix-multiplication.jar user/problem4/input user/problem4/output`  
Or run `run.sh` with default settings  
## How to view results
`hadoop fs -cat user/problem4/output`