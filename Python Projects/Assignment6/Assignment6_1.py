# -------------------------- Assignment 6 Part 1------------------------------------
# Assignment 5 by Jonathan Alvarez                  Due: 6/10/2018
# This is a three part assignment:
# Part 1: In this assignment, write a function isColliding() that takes two ball
# tuples as parameters and computes if they are colliding.  Your function will return
# a Boolean saying whether or not the balls are colliding.
## -------------------------- Part 1 Code ----------------------------------------
import math
print("Today We are going to check whether or not two balls are colliding: ") # Explanation

def isColliding():
    xDiff = (Ball_2[0] - Ball_1[0]) * (Ball_2[0] - Ball_1[0]) # Calculating the difference between two balls
    yDiff = (Ball_2[1] - Ball_1[1]) * (Ball_2[1] - Ball_1[1])

    Distance = math.sqrt(xDiff + yDiff) # Calculating the distance
    RadiiSum = Ball_1[2] + Ball_2[2] # Calculating the sum of two radii

    if Distance <= RadiiSum:
        print("Ball 1 and Ball 2 are colliding! \n")
    else:
        print("Ball 1 and Ball 2 are not colliding! \n")


# Test 1
Ball_1 = (12312, 9432499, 876767)
Ball_2 = (1, 1, 1)
print("\n")
isColliding()
print("Here are the balls coordinates:")
print("Ball 1:", Ball_1, "Ball 2: ",Ball_2)


# Test 2
Ball_1 = (1, 1, 1)
Ball_2 = (1, 1, 1)
print("\n")
isColliding()
print("Here are the balls coordinates:")
print("Ball 1:", Ball_1, "Ball 2: ",Ball_2)

# ------------------------------------- Part 1 Output ----------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment6/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment6/venv/Assignment6_1.py
# Today We are going to check whether or not two balls are colliding:
#
#
# Ball 1 and Ball 2 are not colliding!
#
# Here are the balls coordinates:
# Ball 1: (12312, 9432499, 876767) Ball 2:  (1, 1, 1)
#
#
# Ball 1 and Ball 2 are colliding!
#
# Here are the balls coordinates:
# Ball 1: (1, 1, 1) Ball 2:  (1, 1, 1)
