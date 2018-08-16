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

la $a0, msg4 # Prints message 4
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
msg2: .asciiz "\nException Code = "
msg3: .asciiz "\nIgnore and continue program ...\n"
msg4: .asciiz "\n -program is finished running --\n"
regs: .word 0:3 # Creates space for saved registers 

