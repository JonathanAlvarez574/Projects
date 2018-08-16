# -------------------------- Assignment 6 Part 2------------------------------------
# Assignment 6 by Jonathan Alvarez                  Due: 6/10/2018
# This is a four part assignment:
# Part 2: Find The Error then, write an exception.
# Explain the issue in your own words.  Suggest how to avoid the error.
## -------------------------- Part 2 Code ----------------------------------------
import sys
# set up a list to iterate on
myCheeseList = ["Apple", "Asiago", "Brie", "Caerphilly", "Emmental", "Gloucester", "Gouda", ]
try:
    for i in range(len(myCheeseList)):

        if i == 2:
            del (myCheeseList[4])

        print(i, myCheeseList[i])
except Exception:
    print("Error occured in line:",i)
    print ("Error:", sys.exc_info()[1])


## -------------------------- Part 2 Output ----------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment6/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment6/venv/Assignment6_2.py
# 0 Apple
# 1 Asiago
# 2 Brie
# 3 Caerphilly
# 4 Gloucester
# 5 Gouda
# Error occured in line: 6
# Error: list index out of range
