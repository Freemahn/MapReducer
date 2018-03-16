#!/usr/bin/env bash
gradle fatJar
# Clean input.txt files
hadoop fs -rm -r user/problem1/
# Create directory structure
hadoop fs -mkdir -p user/problem1/input
# Copy input.txt.txt files
hadoop fs -put input user/problem1
# Clean output
hadoop fs -rm -r user/problem1/output
hadoop jar build/libs/average-count-1.0-SNAPSHOT.jar user/problem1/input user/problem1/output
echo "============================================================================="
hadoop fs -cat user/problem1/output/part-r-00000