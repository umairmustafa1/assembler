# Assembler
Basic assembler implemented in Java

## Structure
src/Assembler.java : This class contains the main function and logic of reading from files, removing comments and writing to files after parsing.  
src/Parser.java: This class contains logic for parsing a valid A and C instruction.  
src/SymbolTable.java: This class maintains the symbol table used by Assembler.  

## Compilation Instructions
javac Assembler.java  
java Assembler "[File Path]"  