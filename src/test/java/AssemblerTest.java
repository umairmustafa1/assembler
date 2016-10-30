import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Test Class for Assembler
 */
public class AssemblerTest {
    @Test
    public void main() throws Exception {
        //dry tests
        testFile("Add");
        testFile("Max");
        testFile("MaxL");
        testFile("Pong");
        testFile("PongL");
        testFile("Rect");
        testFile("RectL");

        //wet tests
        testFile("Fill");
        testFile("mult");

    }

    private void testFile(String fileName){
        Assembler.main(new String[]{"D:\\Dropbox\\UChicago\\52011\\Week 4\\Assembler\\src\\test\\resources\\" + fileName +".asm"});

        Path testHackPath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Week 4\\Assembler\\src\\test\\resources\\" + fileName +".hack");
        Path masterHackPath = Paths.get("D:\\Dropbox\\UChicago\\52011\\Week 4\\Assembler\\src\\test\\resources\\" + fileName +"Master.hack");

        try(Stream<String> testLines = Files.lines(testHackPath, Charset.defaultCharset())){
            try(Stream<String> masterLines = Files.lines(masterHackPath,Charset.defaultCharset())){
                String[] testLinesArray = testLines.toArray(String[]::new);
                String[] masterLinesArray = masterLines.toArray(String[]::new);

                assertEquals(masterLinesArray.length, testLinesArray.length);

                for(int i = 0; i < testLinesArray.length ; i++){
                    assertEquals(masterLinesArray[i],testLinesArray[i]);
                }
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}