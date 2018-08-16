# Assignment 10                             Due: 6/24/18
# CS 3A
# Jonathan Alvarez
# Part 1:
# Write a factorial function using Loops
# -------------------------- Part 1 Source Code ------------------------------------
print("Today we will be writing a factorial function:")

def factorialI(n):
    factorial = 1
    for i in range(n):
        factorial *= i + 1
    return factorial

try:
    n = int(input("Input a number to compute the factiorial : "))
    print("Factorial of", str(n), ":", "\n", factorialI(n))
except Exception:
    print("Try again!")
    n = int(input("Input a number to compute the factiorial : "))
    print("Factorial of", str(n), ":", "\n", factorialI(n))

l = "111111111111"
print(len(l))

# ------------------------------- Part 1 Run--------------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/Assignment10_1.py
# Today we will be writing a factorial function:
# Input a number to compute the factiorial : 9
# Factorial of 9 :
#  362880
#
# Today we will be writing a factorial function:
# Input a number to compute the factiorial : 180
# Factorial of 180 :
#  200896062499134299656951336898466838917540340798867777940435335160044860953395980941180138112097309735631594101037399609671032132186331495273609598531966730972945653558819806475064353856858157445040809209560358463319644664891114256430017824141796753818192338642302693327818731986039603200000000000000000000000000000000000000000000