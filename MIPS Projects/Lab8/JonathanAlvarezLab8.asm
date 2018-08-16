# Jonathan Alvarez
# Due: 6/11/2018
##							 Lab 8: Pipelining  
# Student ID: 20190729 	

.data 
lName: .asciiz "My last name is Alvarez\n"
myId: .asciiz "My Student ID is 20190729\n"


.text 

la $a0, lName # Prints Last Name
li $v0, 4
syscall

la $a0, myId # Prints Student ID
li $v0, 4
syscall



add $3, $2, $3 ## RAW hazard is occur
nop 		## NOP needed because register was being used next
lw $4, 100($3)
sub $7, $6, $2 
nop		## NOP needed becayse needed to be used next
xor $6, $4, $3
