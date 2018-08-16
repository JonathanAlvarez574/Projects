#Assignment 6
#Jonathan Alvarez 								Student ID: 20190729
#In this lab we're going to use loops to do calcualtions and using handling exceptions 
.data
lName: .asciiz "My last name is Alvarez\n"
myId: .asciiz "My student ID is 20190729\n"
sum: .asciiz " + "
Ex: .asciiz "Today I will be calculating the sum of integers: 1*2  +  2*3 + ... + 10*11. \n"
Ex2: .asciiz "Sum: "
z: .asciiz "0"
line: .asciiz "\n"
Eq: .asciiz " = "

.text
la $a0, lName # Prints last name
li $v0, 4
syscall 

la $a0, myId # Prints student ID
li $v0, 4
syscall 

la $a0, Ex # Prints out explaination
li $v0, 4
syscall

la $a0, Ex2 # Prints out "sum:" 
li $v0, 4
syscall

la $a0,z # Prints out zero 
li $v0,4
syscall

Loop1: 
li $t2,1 #t2 = 1
li $t0,0  #t0 = 0

top: bgt $t2, 10,end # start of the first loop 

addi $t2, $t2, 1 #t0++

loop2: bgt $t0, 9,end # start of the second loop 

addi $t0, $t0, 1 #t0++

mult $t0,$t2 # t0*t2

mflo $s1

add $t3, $s1,0 # sums t0 * t2 as it increments 

la $a0, sum #prints "+"
li $v0, 4
syscall
 
move $a0, $t3 #prints the value of the sum of integers
li $v0, 1
syscall 

add $t1,$t3,$t1 # Sum up all the values

b top # jumps back to top of the loop 


end:
la $a0, Eq # Prints "=" 
li $v0,4
syscall

move $a0, $t1 # Prints out the sum
li $v0,1
syscall

li $v0,10 #ends program
syscall

# Exception Handler:

.ktext 0x80000180 
move $k0, $at # $k0 = $at
la $k1, regs # $k1 = address of registers

sw $k0, 0($k1) # save $at
sw $v0, 4($k1) # save $v0
sw $a0, 8($k1) # save $a0

la $a0, msg1 #prints message 1
li $v0, 4 
syscall 

mfc0 $a0, $14 # Print EPC in hexadecimal
li $v0, 34 
syscall 

la $a0, msg2 # Prints message 2
li $v0, 4 
syscall 

mfc0 $a0, $13 # $a0 = cause
srl $a0, $a0, 2 # shift right by 2 bits
andi $a0, $a0, 31 # $a0 = exception code

li $v0, 1 # Print exception code
syscall 

la $a0, msg3 # Prints message 3
li $v0, 4 
syscall 

la $k1, regs # k1 = registers

lw $at, 0($k1) # restore $at
lw $v0, 4($k1) # restore $v0
lw $a0, 8($k1) # restore $a0

mtc0 $zero, $8 # clear vaddr
mfc0 $k0, $14 # $k0 = EPC
addiu $k0, $k0, 4 # Increment $k0 by 4

mtc0 $k0, $14 # EPC = point to next instruction
eret # exception return: PC = EPC

# kernel data:
.kdata
msg1: .asciiz "\nException caused by instruction located at address: "
msg2: .asciiz "\nException Refrence Code = "
msg3: .asciiz "\n Continue program ...\n"
regs: .word 0:3 # Creates space for saved registers 

