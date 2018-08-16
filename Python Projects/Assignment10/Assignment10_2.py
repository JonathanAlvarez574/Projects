# Assignment 10                             Due: 6/24/18
# CS 3A
# Jonathan Alvarez
# Part 2:
# Write a factorial function using recursion
# -------------------------- Part 2 Source Code ------------------------------------


def factorialR(n):
    if n == 1:
        return 1
    else:
        result = n * factorialR(n - 1)
        return result


try:
    n = int(input("Input a number to compute the factiorial : "))
    print("Factorial of", str(n), ":", "\n", factorialR(n))
except Exception:
    print("Try again!")
    n = int(input("Input a number to compute the factiorial : "))
    print("Factorial of", str(n), ":", "\n", factorialR(n))

# -------------------------- Part 2 Run -------------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/Assignment10_2.py
# Input a number to compute the factiorial : 9
# Factorial of 9 :
#  362880
#
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/Assignment10_2.py
# Input a number to compute the factiorial : 180
# Factorial of 180 :
#  200896062499134299656951336898466838917540340798867777940435335160044860953395980941180138112097309735631594101037399609671032132186331495273609598531966730972945653558819806475064353856858157445040809209560358463319644664891114256430017824141796753818192338642302693327818731986039603200000000000000000000000000000000000000000000