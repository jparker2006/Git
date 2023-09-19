import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class IndexTester {
    public String testFile = "index.txt";

    @Test
    @DisplayName("Testing init")
    public void testInitialize() throws Exception {
        index.init();
        File objects = new File("./objects/");
        assertTrue(objects.exists() && objects.isDirectory());
        File file = new File("index");
        assertTrue(file.exists());
    }

    @Test
    @DisplayName ("Test if add creates new file and updates Index")
    void testAdd() throws Exception {
        Git g = new Git ();
        g.init();
        g.add(testFile); 
        String hash = blob.getStringHash(Utils.readFileToString(testFile)); 
        File f = new File ("./objects/" + hash);
        assertTrue ("Git add did not create objects folder", f.exists());

        //test if it added something to the index file
        String lineInIndex = Utils.readLineWhichContains("index", testFile);
        assertTrue ("Git add didn't update index file", lineInIndex.equals(testFile + " : " + hash));
    }
}
