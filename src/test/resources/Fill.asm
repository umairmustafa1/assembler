// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

//setting value of screen index which is used to keep track of last register on which change was made
@SCREEN
D=A
@screenindex
M=D

//infinite loop
(LOOP)

//storing KBD into D
@KBD
D=M

//jump to BLACK if any key is being pressed
@BLACK
D;JNE
//jump to WHITE if no key is being pressed
@WHITE
D;JEQ

(BLACK)
//this makes sure that screen index does not cross the max limit of the screen
@KBD
D=A
@screenindex
D=D-M
@LOOP
//jump if KBD - screenindex <= 0 (KBD is exclusive)
D;JLE

//setting screen index bit and updating screen index value
@screenindex
A=M
M=-1
@screenindex
M=M+1

@ENDOFIF
0;JMP

(WHITE)
//this makes sure that screen index is greater than or equal to min value of screen
@SCREEN
D=A
@screenindex
D=D-M
@LOOP
//jump if SCREEN - screenindex > 0 (SCREEN is inclusive)
D;JGT

//setting screen index bit and updating screen index value
@screenindex
A=M
M=0
@screenindex
M=M-1

@ENDOFIF
0;JMP

//label of if branch between black and white
(ENDOFIF)

@LOOP
0;JMP