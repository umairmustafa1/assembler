import java.util.Hashtable;
import java.util.Map;

/**
 * Parser class to convert A and C instruction to their binary equivalent
 */
public class Parser {
    private final Map<String,String> compMap;
    private final Map<String,String> destMap;
    private final Map<String,String> jmpMap;

    public Parser(){
        compMap = new Hashtable<>();
        destMap = new Hashtable<>();
        jmpMap = new Hashtable<>();
        fillMaps();
    }

    /**
     * Parses A instruction
     * @param input A instruction
     * @return Binary format of A instruction
     */
    public String parseAInstruction(String input){
        //Converts A instruction to binary string. OR operation with 0x10000 to get 15 digit binary string with leading 0's
        return "0" + Integer.toBinaryString(0x10000 | Integer.parseInt(input.substring(1))).substring(2);
    }

    /**
     * Parses C Instruction
     * @param input C Instruction
     * @return Binary Format of C Instruction
     */
    public String parseCInstruction(String input){
        String dest = (input.contains("=") ? input.substring(0,input.indexOf("=")) : "");//In case dest is missing it will be an empty string
        String comp = (input.contains("=") ?
                input.substring(input.indexOf("=") + 1, (input.contains(";") ? input.indexOf(";") : input.length())) //If C Instruction has dest part
                :
                input.substring(0, (input.contains(";") ? input.indexOf(";") : input.length()))//If C Instruction does not have comp part TODO if there is no dest or jmp part
        );

        String jmp = (input.contains(";") ? input.substring(input.indexOf(";") + 1, input.length()) : "");//In case jmp is missing, it will be an empty string

        dest = destMap.getOrDefault(dest,"000");
        comp = compMap.get(comp);
        jmp = jmpMap.getOrDefault(jmp,"000");

        return "111" + comp + dest + jmp;
    }

    /**
     * Helper Function to fill dest, comp and jmp assembly to binary maps
      */
    private void fillMaps(){
        compMap.put("0","0101010");
        compMap.put("1","0111111");
        compMap.put("-1","0111010");
        compMap.put("D","0001100");
        compMap.put("A","0110000");
        compMap.put("!D","0001101");
        compMap.put("!A","0110001");
        compMap.put("-D","0001111");
        compMap.put("-A","0110011");
        compMap.put("D+1","0011111");
        compMap.put("A+1","0110111");
        compMap.put("D-1","0001110");
        compMap.put("A-1","0110010");
        compMap.put("D+A","0000010");
        compMap.put("D-A","0010010");
        compMap.put("A-D","0000111");
        compMap.put("D&A","0000000");
        compMap.put("D|A","0010101");
        compMap.put("M","1110000");
        compMap.put("!M","1110001");
        compMap.put("-M","1110011");
        compMap.put("M+1","1110111");
        compMap.put("M-1","1110010");
        compMap.put("D+M","1000010");
        compMap.put("D-M","1010011");
        compMap.put("M-D","1000111");
        compMap.put("D&M","1000000");
        compMap.put("D|M","1010101");

        destMap.put("M","001");
        destMap.put("D","010");
        destMap.put("MD","011");
        destMap.put("A","100");
        destMap.put("AM","101");
        destMap.put("AD","110");
        destMap.put("AMD","111");

        jmpMap.put("JGT","001");
        jmpMap.put("JEQ","010");
        jmpMap.put("JGE","011");
        jmpMap.put("JLT","100");
        jmpMap.put("JNE","101");
        jmpMap.put("JLE","110");
        jmpMap.put("JMP","111");
    }
}
