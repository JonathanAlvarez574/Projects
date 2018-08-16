# Assignment 10                             Due: 6/24/18
# CS 3A
# Jonathan Alvarez
# Part 3:
# Compare the two previous runs to see which one is faster.
# -------------------------- Part 3 Source Code ------------------------------------

from timeit import default_timer as timer

# Part 1

def factorialI(n):  # Iterative
    factorial = 1
    for i in range(n):
        factorial *= i + 1
    return factorial

# Part 2

def factorialR(n): # Recursive
    if n == 1:
        return 1
    else:
        result = n * factorialR(n - 1)
        return result


def func_to_measureI():
    result = factorialI(180)

print("starting timer")
start = timer()
for i in range(100000):
    func_to_measureI()
end = timer()

elapsed = end - start
print("elapsed time:", elapsed, "secs")


def func_to_measureR():
    result = factorialR(180)


print("starting timer")
start = timer()
for i in range(100000):
    func_to_measureR()
end = timer()

elapsed = end - start
print("elapsed time:", elapsed, "secs")

# ------------------------------- Part 3 Run ------------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment10/Assignment10_3.py
# starting timer
# elapsed time: 2.683614251000108 secs
# starting timer
# elapsed time: 4.752065540989861 secs

# There are various reasons that iterative is a lot faster then recursive.
# One of the major reason is because iteration is doing same thing many times.
# This makes the elapsed time a lot slower.
# Then with recursion it is breaking down the big problem into smaller problem or problems,
# then recursion solves it in the same manner provided we know the solution for the smallest possible input.

