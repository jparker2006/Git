import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class BlobTester {
    @Test
    @DisplayName("Testing add")
    public void testCreateBlob() throws Exception {
        File file = new File("testfile.txt");
        String sFill = "This is a test";
        Files.write(file.toPath(), sFill.getBytes());
        StringBuilder i = new StringBuilder();
        blob.write(blob.hashStringToSHA1(sFill), i);
        File blobFile = new File("./objects/" + blob.hashStringToSHA1(sFill));
        assertTrue(blobFile.exists());
        file.delete();
        blobFile.delete();
    }

    @Test
    @DisplayName("Testing remove blob")
    public void testRemoveBlob() throws Exception {
        File file = new File("testfile.txt");
        String sFill = "File contents";
        Files.write(file.toPath(), sFill.getBytes());
        index.init();
        index.commit(file.getAbsolutePath());
        index.remove(file.getAbsolutePath());
        File object = new File("./objects/" + blob.hashStringToSHA1(file.getAbsolutePath()));
        assertFalse(object.exists());
        file.delete();
    }
}
