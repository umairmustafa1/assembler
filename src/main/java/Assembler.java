import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Assembler {

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Usage : java Assembler \"[File Path]\"");
        }
        else{
            Path inputFilePath = Paths.get(args[0]);
            String inputFileName = inputFilePath.getFileName().toString();
            Path outputFilePath = inputFilePath.resolveSibling(inputFileName.substring(0,inputFileName.lastIndexOf(".")) + ".hack");

            Parser parser = new Parser();
            SymbolTable symbolTable = new SymbolTable();

            try(Stream<String> lines = Files.lines(inputFilePath, Charset.defaultCharset())){
                int lineCounter = 0;
                List<String> outputLines = new ArrayList<>();//Array list to hold lines without comments and labels

                //Loop to remove comments and store ROM address of labels
                for (String line : lines.collect(Collectors.toList())) {
                    if(line.startsWith("("))
                        symbolTable.addEntry(line.substring(1, line.length() - 1), lineCounter);
                    else if(!line.trim().startsWith("//") && !(line.trim().isEmpty())){//Removing lines with white space or just comments
                        lineCounter++;
                        outputLines.add(line.substring(0, line.contains("//") ? line.indexOf("//") : line.length()).trim());
                    }
                }

                //Array of length 1 to store current ram address, its an array because variables have to be final if they need to be changed in lambda expressions
                final Integer[] ramAddress = new Integer[1];
                ramAddress[0] = 15;

                //Loop to parse A and C instructions
                List<String> finalOutput =
                        outputLines.stream()
                                .map(a -> {
                                    if(a.startsWith("@")){
                                        String key = a.substring(1, a.length());
                                        if(key.matches("\\d+"))//If A Instruction does not have symbol in it just return it as it is
                                            return a;
                                        else {//If A instruction has symbol in it
                                            if(symbolTable.contains(key))
                                                return "@" + symbolTable.getAddress(key);
                                            else {
                                                ramAddress[0]++;
                                                symbolTable.addEntry(key, ramAddress[0]);
                                                return "@" + ramAddress[0];
                                            }
                                        }
                                    }else
                                        return a;
                                })
                                .map(a -> a.startsWith("@") ? parser.parseAInstruction(a) : parser.parseCInstruction(a))//Parses A instruction or C Instruction
                                .collect(Collectors.toList());

                Files.write(outputFilePath,finalOutput,Charset.defaultCharset());
            }catch(IOException ex){
                System.out.println("No such file exists : " + inputFilePath);
            }
        }
    }
}
