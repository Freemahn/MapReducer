This repo contains 5 small tasks.
Problems 1-4 are Java-8 Gradle projects.  
Problem 5 is python-written mapreducer.  
All of them use Hadoop.


That [link](https://hadoop.apache.org/docs/stable/hadoop-project-dist/hadoop-common/SingleCluster.html) helps you to install and run hadoop on local machine.  
You will also need Java 8.

## How to build and run
Run `gradle fatJar` to build jar file.  
 

Create directories with input files and copy them to HDFS 
- `hadoop fs -mkdir -p user/problem4/input`
- `hadoop fs -put input user/problem4`  

Then run `hadoop jar path/to/jar path/to/input path/to/output` in order to execute program on input given.   
Or you can run `run.sh` scripts provided to each project with default settings
Use `hadoop fs -cat user/problem4/output/part-r-00000`