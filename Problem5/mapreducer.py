#!/usr/bin/env python
"""mapreducer.py"""

import sys
from sortedcontainers import SortedList

maxValues = SortedList()
# input.txt.txt comes from STDIN (standard input.txt.txt)
# mapper
for line in sys.stdin:
    # remove leading and trailing whitespace
    line = line.strip()
    # split the line into words
    numbers = line.split()
    for number in numbers:

        # parse string to digit
        try:
            number = float(number)
        except ValueError:
            continue

        maxValues.add(number)
        # drop the smallest max value if we found new one
        # reducer
        if (len(maxValues) > 10):
            maxValues.pop(0)
# print results
for v in maxValues:
    print(v)
