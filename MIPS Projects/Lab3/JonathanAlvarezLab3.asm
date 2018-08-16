# Lab 3 CS 10 
# Short program to demonstrate MIPS arithmetic
# Jonathan Alvarez
# Due: 5/1/18
#Student ID: 20190729
#Last name: Alvarez

.data
main:

myId: .word 30 #Sum of numbers in Student ID

nLet: .word 7 #Number of Letters in Last Name

Mes0: .asciiz "My Student ID: 20190720 \n""

Mes1: .asciiz "My family name is Alvarez \n"

Mes2: .asciiz "The value of myId is 30 \n"

Mes3: .asciiz "The number of characters in my last name is 7 \n"

Ex1: .asciiz "\n nLet + myId = "

Ex2: .asciiz "\n nLet - myId = "

Ex3: .asciiz "\n nLet * 4 = "

Ex4: .asciiz "\n even adjust(nLet + myId) /2 = "

Sub: .word 0 #Setting variable for subtraction

Mult: .word 0 #Setting variable for multiplication

Add: .word 0 #Setting variable for Addtion 

Ead: .word 0 #Setting variable for even adjust

even: .asciiz "is even"

.text 

lw $s0, myId #s0 = myId

lw $s1, nLet  #s1 = nLet

add $t0,$s0,$s1 #t0 = s0 + s1 -> myId + nLet

sub $t1,$s0,$s1 #t1 = s0 - s1 -> myId - nLet

li $t2,4 #t2 = 4

mul $t3,$s0,$t2 #t3 = s0 * t2 -> myId * 4

lw $t4, Add # t4 = Add

lw $t5, Sub # t5 = Sub

lw $t6, Mult #t6 = Mult

li $t7, 2 #t7 =2

sub $t8,$t0,1 #t8 = t0 - 1 -> Sum - 1 (Even Adjustment)

div $t9,$t8,$t7 #t9 = t8/t7 -> Ead/2 
 
#Even Adjust

andi $t4, $t0, 2 # checking if Ex1 is even
bne  $t4, $zero, Ead #if Sum is odd go straight to Ead/2


# Pringting Messages

la $a0, Mes0 #student ID
li $v0, 4
syscall
 

la $a0, Mes1 #Last Name
li $v0, 4
syscall 


la $a0, Mes2 # value for myId
li $v0, 4
syscall 

la $a0, Mes3 #number of characters in nLet
li $v0, 4
syscall 

la $a0, Ex1 #Expression 1
li $v0, 4
syscall 

#ADD

#moving the sum 
move $a0,$t0 #a0 = t4

#storing the sum to varible Add
sw $t0,Add  #t0 = Add

li $v0,1
syscall 

#SUB

la $a0, Ex2 #Expression 2
li $v0, 4
syscall 

#moving the Sub to $t1

move $a0,$t1 #a0 = t1

#storing the subtract
sw $t1, Sub #t1 = Sub

li $v0,1
syscall 

la $a0, Ex3 #Expression 3
li $v0, 4
syscall 

#Mult

#moving the Mult to $t2

move $a0,$t3 #a0 = t2

#storing the Mult
sw $t3, Mult #t2 = Mult

li $v0,1
syscall 

#Even adjust(nLet + myId) /2

la $a0, Ex4 #Expression 4
li $v0, 4
syscall 


move $a0, $t9 # Moving t9

#Storing Ead
sw $t9, Ead # t9 = Even Adjust/2

li $v0, 1
syscall 


li $v0,10 #Exits Syscall
syscall 



