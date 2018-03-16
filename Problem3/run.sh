#!/usr/bin/env bash
gradle fatJar

# Clean input.txt files
hadoop fs -rm -r user/problem3/
# Create directory structure
hadoop fs -mkdir -p user/problem3/input
# Copy input.txt.txt files
hadoop fs -put input user/problem3
# Clean output
hadoop fs -rm -r user/problem3/output
hadoop jar build/libs/word-count-1.0-SNAPSHOT.jar user/problem3/input user/problem3/output
echo "============================================================================="
hadoop fs -cat user/problem3/output/part-r-00000