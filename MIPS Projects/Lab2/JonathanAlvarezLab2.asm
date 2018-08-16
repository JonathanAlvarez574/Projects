# Lab 2 
# Program to demonstrate how to print in MIPS.
# Name:  Jonathan Alvarez
# Due Date:  4/23/17

	.data
message: 	.asciiz "Hello World \n"
lastName:	.asciiz	"MAlvarez\n"
FHID:		.asciiz "20190729\n"
Sylbs:		.asciiz "1 Midterm, Final 40 points, Assignmnets due every Tuesday\n" 

	.text
main:				
   la $a0, message  # load the addr of hello_msg into $a0.
   li $v0, 4         # 4 is the print_string syscall.
   syscall 		# do the syscall.
  
   la $a0, lastName #Load the addr of lastname message into $a0
   li $v0, 4  		# 4 is the print_string syscall.
   syscall          	 # do the syscall.
   
   la $a0, FHID  #Load the addr of Foothill ID message into $a0
   li $v0, 4  		# 4 is the print_string syscall.
   syscall          	 # do the syscall.
   
   la $a0, Sylbs  #Load the addr of Foothill ID message into $a0
   li $v0, 4  		# 4 is the print_string syscall.
   syscall          	 # do the syscall.
   
   
   li $v0, 10        # 10 is the exit syscall.
   syscall           # do the syscall

