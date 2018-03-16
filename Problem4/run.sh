#!/usr/bin/env bash
gradle fatJar
# Clean input.txt files
hadoop fs -rm -r user/problem4/
# Create directory structure
hadoop fs -mkdir -p user/problem4/input
# Copy input.txt.txt files
hadoop fs -put input user/problem4
# Clean output
hadoop fs -rm -r user/problem4/output
hadoop jar build/libs/matrix-multiplication-1.0-SNAPSHOT.jar user/problem4/input user/problem4/output
echo "============================================================================="
hadoop fs -cat user/problem4/output/part-r-00000