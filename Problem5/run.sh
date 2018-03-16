#!/usr/bin/env bash
pip2 install sortedcontainers

# Clean input files
hadoop fs -rm -r user/problem5/
# Create directory structure
hadoop fs -mkdir -p user/problem5/input
# Copy input.txt files
hadoop fs -put input user/problem5
# Clean output
hadoop fs -rm -r user/problem5/output
# share/hadoop/tools/lib/hadoop-*streaming*.jar
hadoop jar hadoop-*streaming*.jar \
-file mapreducer.py -mapper mapreducer.py \
-input user/problem5/input -output user/problem5/output


echo "============================================================================="
hadoop fs -cat user/problem5/output/part-00000


