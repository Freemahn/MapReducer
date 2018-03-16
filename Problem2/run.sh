#!/usr/bin/env bash
gradle fatJar

# Clean input.txt files
hadoop fs -rm -r user/problem2/
# Create directory structure
hadoop fs -mkdir -p user/problem2/input
# Copy input.txt.txt files
hadoop fs -put input user/problem2
# Clean output
hadoop fs -rm -r user/problem2/output
hadoop jar build/libs/statistic-count-1.0-SNAPSHOT.jar user/problem2/input user/problem2/output
echo "============================================================================="
hadoop fs -cat user/problem2/output/part-r-00000

