# Assignment 7						Due: 6/5/18
# Student ID: 20190729 
# Jonathan Alvarez
# Assignment: Form a execption handler for this error
.data
lName: .asciiz "My Last name is Alvarez\n"
myId: .asciiz "My Student ID is 20190729\n"
.text
.globl main

main: 

la $a0, lName # Prints last name 
li $v0, 4
syscall

la $a0,myId #prints Student ID
li $v0, 4
syscall

li $t1, 300
li $t0, 0
div $a0, $t1, $t0 # Do $a0 = $t1 / $t0

li $v0, 1  # Print result as an integer
syscall


li $v0, 10  # and exitsyscall