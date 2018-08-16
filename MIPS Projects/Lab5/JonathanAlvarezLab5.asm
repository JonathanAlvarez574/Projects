## Lab 5 CS 10 JonathanAlvarezLab5.asm
## Program to demonstrate the jal and jr MIPS instructions
## To show the use of stacks using average of two numbers

.data
Ex: 	.asciiz "Hello! Today I will be adding the average of two numbers down a stack list. \n"
mL: 	.asciiz "My Last Name is: "
mI:	.asciiz "My Student ID is: "
lNam:   .asciiz "Alvarez\n"
myId:   .asciiz "20190729\n"
num1:	.word 0 #creates variable num1 
num2:	.word 0 #creates variable num2 
addLine:.asciiz "\n"
n1: 	.asciiz "Num1: " # Creates a string for varible num1
n2:	.asciiz "Num2: " # Creates a string for varible num2
av:	.asciiz "The Average is: " # Creates variable for average
ave: 	.word 0 # Creates variable for average value

.text
#Inital Explaination:
main:

la $a0, Ex #Prints Explaination
li $v0, 4
syscall 

la $a0, mL # Explains Last  name
li $v0, 4
syscall

la $a0,lNam ## Print Last name
li $v0,4
syscall


la $a0, mI #Explains my student ID
li $v0, 4
syscall

la $a0,myId ## Print Student ID
li $v0,4
syscall

## Loading Imediate values into numbers variables:

la $a0,n1 #Prints "num1:"
li $v0,4
syscall

lw $s0,num1 # s0 = num1
li $s0,9 #num1 = 9
move $a0,$s0 #moving value of a0 into s0
sw $s0, num1 #storing num1 as s0

li $v0,1 #prints value of num1 
syscall

la $a0, addLine  #adds line between num1 and num2
li $v0, 4
syscall

la $a0,n2 #Prints "num2:"
li $v0,4
syscall

lw $s1,num2 # s1 = num2
li $s1,3 #num2 = 3
move $a0,$s1 #moving value a0 into s1
sw $s1, num2 #storing num2 as s1

li $v0,1 #print value of num2
syscall

#stores words onto stack:
sw $s0,4($sp)
sw $s1,8($sp)

la $a0, addLine  #makes a new line
li $v0, 4
syscall

jal Average #jumps to average calcuation

lw $t0, ($sp) #loads information from stack

addu $sp,$sp, 24 #Destory stack frame

move $a0,$t0 #moves value of a0 into t1
li $v0,1
syscall 

li $v0, 10 #ends program
syscall 

##Calculating Average of two numbers:
Average: 
lw $t1,4($sp)
lw $t2,8($sp)

la $a0, av #Prints explaination of average
li $v0, 4
syscall 

add $t0,$t1,$t2 #adds two numbers t1+t2 
div $t0,$t0,2 # averages out the two numbers

sw $t0,($sp) #stores average t0 onto stack

jr $ra #goes back ra (jal Average) to finish program
