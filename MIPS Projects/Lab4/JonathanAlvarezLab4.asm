##Jonathan Alvarez									Due: 5/8/18
##Lab 4 CS10 Jonathan Alvarez Lab 4
##Compute the sum of all odd integers from 1 up to the user supplied value for 'top'.

.data
userInput: .word 0
Ex: .asciiz "Today we will be added all odd numbers adding up to your inputed number.\n"
prompt: .asciiz "\nPlease insert a number:"
result: .asciiz "\nThe sum of all numbers up to your number is:" 
plus: .asciiz " + " #addition sign
lNam: .asciiz  "Last Name: Alvarez\n" 
myId: .asciiz "Student ID: 20190729\n"
oddS: .word 0 #Sum of odd ints
z: .asciiz "0" #zero
sum: .asciiz "Sum:"
.text
main:

la $a0, lNam #print last name
li $v0, 4
syscall 

la $a0, myId #print student ID
li $v0, 4
syscall 

la $a0, Ex #print explaination 
li $v0, 4
syscall 


la $a0, prompt #print prompt 
li $v0, 4
syscall 

#Gathering User input 

li $v0, 5 # user input ino $v0
syscall 
move $t0, $v0 #moves user input into $t0


lw $t4, userInput #storing user input ($t0) into userInput
move $a0, $t0
sw $t0, userInput #t0 = userInput

la $a0, sum #print "Sum:"
li $v0,4 
syscall 


la $a0, z #starts off the sum with 0
li $v0,4
syscall 

#Loop Start

li $t1, 1 #t1 = 1

loopTop:
bgt $t1,$t0,loopEnd #Start of loop at 1 end at users input number

#Determining if number is even or odd
addi $t3, $zero, 2 # Set divisor to 2
div $t1, $t3
mfhi $t3           # Save remainder
beqz $t3, even #if remainder = zero its even

la $a0, plus #print plus 
li $v0, 4
syscall 

j odd #if odd jump to odd loop


## Odd calcuation
odd:

move $a0, $t1 #pint only odd values
li $v0, 1
syscall 

add $t4,$t1,$t4 #t4 = Storing and adding all odd sums up to userInput

add $t1,$t1,1 #t1++


j loopTop

## Even Calcuation
even:
add $t1,$t1,1
j loopTop


##End of loop

loopEnd:

la $a0, result #prints results
la $v0, 4 
syscall


lw $t9,oddS #t9 = odd sum
move $a0,$t4 #moving a0 to t4
sw $t4,oddS #storing data of t4 into odd sum

li $v0,1 #printing odd sum
syscall 


li $v0, 10 #Ends Syscall
syscall 
