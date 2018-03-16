## How to build
`sortedcontainers` is required to run this program  
`pip2 install sortedcontainers`

## How to run
Considering hadoop installed in %HADOOP_HOME%


`hadoop jar hadoop-*streaming*.jar \
 -file mapreducer.py -mapper mapreducer.py \
 -input path/to/input -output /path/to/output`  
Example  
`hadoop jar hadoop-*streaming*.jar \
 -file mapreducer.py -mapper mapreducer.py \
 -input user/problem5/ -output user/problem5/output`  
Or run `run.sh` with default settings  
## How to view results
`hadoop fs -cat user/problem5/output`