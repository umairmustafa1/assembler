// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)

//load RO to D
@R0
D=M
//load D to variable i
@i
M=D
//set result variable to 0
@result
M=0
(LOOP)
//load loop counter(R0) in D
@i
D=M
//jump to end of loop if R0 is 0
@END
D;JEQ
//load R1 into D
@R1
D=M
//add R1 to result on each iteration of loop
@result
M=D+M
//decrement loop counter by 1
@i
M=M-1
//jump to the loop label
@LOOP
0;JMP
(END)
//load result variable
@result
D=M
//store results in R2
@R2
M=D
//Ending with an infinite loop
(INFINITE)
@INFINITE
0;JMP