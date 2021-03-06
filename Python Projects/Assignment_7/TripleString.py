# -------------------------- Assignment 7 ------------------------------------
# Assignment 7 by Jonathan Alvarez                  Due: 6/15/2018
# In this Assignment we need to create a class called TripleString
# The purpose of the TripleString class is to create objects that can each hold 3 strings.
## -------------------------- Source Code ----------------------------------------


class TripleString:
    """ encapsulates a 3-string object """

    # ------------------- intended class constants ------------------------------------
    MAX_LEN = 50
    MIN_LEN = 1
    DEFAULT_STRING = "(undefined) "

    # ------------ constructor method --------------------------------------------------
    def __init__(self,
                 string1=DEFAULT_STRING,  # Defining each variable
                 string2=DEFAULT_STRING,
                 string3=DEFAULT_STRING):

        if not self.set_string1(string1):
            self.string1 = TripleString.DEFAULT_STRING  # Defining parameters for our constructor

        if not self.set_string2(string2):
            self.string2 = TripleString.DEFAULT_STRING

        if not self.set_string3(string3):
            self.string3 = TripleString.DEFAULT_STRING

    # ----------------------- mutator ("set") methods -------------------------------
    def set_string1(self, string1): # Defining our "setters" for our strings
        if not self.valid_string(string1):
            return False
        else:
            self.string1 = string1
            return True

    def set_string2(self, string2):
        if not self.valid_string(string2):
            return False
        else:
            self.string2 = string2
            return True

    def set_string3(self, string3):
        if not self.valid_string(string3):
            return False
        else:
            self.string3 = string3
            return True

    # ------------------------ accessor ("get") methods -------------------------------

    def get_string1(self): # Defining our getters for our class
        return self.string1

    def get_string2(self):
        return self.string2

    def get_string3(self):
        return self.string3

    # -------------------------- To String Method --------------------------------
    def __str__(self):  # Defining our toString Class
        toString = "strings are:\n\t" + self.string1 + "\n\t" + self.string2 \
                   + "\n\t" + self.string3 + "\n"
        return toString

    # ------------------------ helper methods for entire class -----------------
    @classmethod # Helper methods to check if input is a proper string
    def valid_string(cls, the_str):
        if type(the_str) != str \
                or \
                not (cls.MIN_LEN <= len(the_str) <= cls.MAX_LEN):
            return False
        # else
        return True

# --------------------------- CLIENT --------------------------------------------------

# Create 4 TripleString objects

# Printing Explaination our this Assignment
print("Hello Today we are writing a class that takes on the parameters"
      + " of three strings!\n")

# TEST 1
# Printing out the Default strings without overriding
print("Test 1 (Default):")

triple_string_num_1 = TripleString()
print(triple_string_num_1)

# TEST 2

print("Test 2:")
triple_string_num_2 = TripleString("Hi", "I'm", "Jonathan") # Overriding Default constructors
print(triple_string_num_2)

print("Modifying Test 2:")
triple_string_num_2.set_string1("Hello") # Using mutators to override it again
triple_string_num_2.set_string3("Sleepy")
print(triple_string_num_2)

# TEST 3

print("Test 3:")
triple_string_num_3 = TripleString()
print(triple_string_num_3)

print("Testing mutator with one error:")

triple_string_num_3.set_string1("The") # Using mutators to override the default strings
triple_string_num_3.set_string2("Cat")
triple_string_num_3.set_string3(1) # With one error mutator to check if/else statement works
print(triple_string_num_3)

print("Testing mutator without an error:")
triple_string_num_3.set_string3("in the hat") # Overiding the error mutator to work
print(triple_string_num_3)

# TEST 4
print("Test 4:")

triple_string_num_4 = TripleString("Thing 1", "Thing 2", "The Cat") # Overriding the default strings
print(triple_string_num_4)

print("Lets test our accessor: ")
print("Retrieving String1: " + triple_string_num_4.get_string1())  # Testing accessor

print("Retrieving String3: " + triple_string_num_4.get_string3())  # Testing accessor

## --------------------------- Assignment 7 Output -----------------------------------------
# /Users/jonathanalvarez/Documents/Python_Projects/Assignment_7/venv/bin/python /Users/jonathanalvarez/Documents/Python_Projects/Assignment_7/TripleString.py
# Hello Today we are writing a class that takes on the parameters of three strings!
#
# Test 1 (Default):
# strings are:
# 	(undefined)
# 	(undefined)
# 	(undefined)
#
# Test 2:
# strings are:
# 	Hi
# 	I'm
# 	Jonathan
#
# Modifying Test 2:
# strings are:
# 	Hello
# 	I'm
# 	Sleepy
#
# Test 3:
# strings are:
# 	(undefined)
# 	(undefined)
# 	(undefined)
#
# Testing mutator with one error:
# strings are:
# 	The
# 	Cat
# 	(undefined)
#
# Testing mutator without an error:
# strings are:
# 	The
# 	Cat
# 	in the hat
#
# Test 4:
# strings are:
# 	Thing 1
# 	Thing 2
# 	The Cat
#
# Lets test our accessor:
# Retrieving String1: Thing 1
# Retrieving String3: The Cat
