import java.util.Hashtable;
import java.util.Map;

/**
 * Class to maintain symbol table
 */
public class SymbolTable {
    private final Map<String,Integer> symbolTable;

    public SymbolTable() {
        symbolTable = new Hashtable<>();
        loadDefaultSymbols();
    }

    /**
     * Helper function to load default symbols
     */
    private void loadDefaultSymbols(){
        symbolTable.put("SP",0x0000);
        symbolTable.put("LCL",0x0001);
        symbolTable.put("ARG",0x0002);
        symbolTable.put("THIS",0x0003);
        symbolTable.put("THAT",0x0004);

        symbolTable.put("R0",0x0000);
        symbolTable.put("R1",0x0001);
        symbolTable.put("R2",0x0002);
        symbolTable.put("R3",0x0003);
        symbolTable.put("R4",0x0004);
        symbolTable.put("R5",0x0005);
        symbolTable.put("R6",0x0006);
        symbolTable.put("R7",0x0007);
        symbolTable.put("R8",0x0008);
        symbolTable.put("R9",0x0009);
        symbolTable.put("R10",0x000a);
        symbolTable.put("R11",0x000b);
        symbolTable.put("R12",0x000c);
        symbolTable.put("R13",0x000d);
        symbolTable.put("R14",0x000e);
        symbolTable.put("R15",0x000f);

        symbolTable.put("SCREEN",0x4000);
        symbolTable.put("KBD",0x6000);
    }

    /**
     * Adds new entry in symbol table
     * @param key key for the symbol table
     * @param value value of the key
     */
    public void addEntry(String key, Integer value){
        symbolTable.put(key, value);
    }

    /**
     * Checks if a key is present in symbol table
     * @param key key for the symbol
     * @return true if key is present in the table false otherwise
     */
    public boolean contains(String key){
        return symbolTable.containsKey(key);
    }

    /**
     * Gets address of a key
     * @param key key for the symbol
     * @return value against the key
     */
    public Integer getAddress(String key){
        return symbolTable.get(key);
    }
}
