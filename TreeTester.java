import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreeTester {
    private static String sFile = "input.txt";

    @Test
    @DisplayName("Testing add")
    void TreeAdd() throws Exception {
        tree tree = new tree();
        String t1 = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
        String t2 = "blob : 640ab2bae07bedc4c163f679a746f7ab7fb5d1fa : input.txt";
        tree.add(t1);
        tree.add(t2);
        assertTrue("Add is not working", tree.getContents().contains(t1) && tree.getContents().contains(t2));
    }

    @Test
    void TreeRemove() {
        tree tree = new tree();
        String t1 = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
        String t2 = "blob : 640ab2bae07bedc4c163f679a746f7ab7fb5d1fa : input.txt";
        tree.add(t1);
        tree.add(t2);
        tree.remove("input.txt");
        assertFalse ("Did not remove file", tree.getContents().contains(t2));
        tree.remove("640ab2bae07bedc4c163f679a746f7ab7fb5d1fa");
        assertFalse ("Did not remove tree ", tree.getContents().contains(t1));
    }

    @Test
    void TreeWriteToFile() throws Exception {
        tree tree = new tree();
        tree.add("blob : 640ab2bae07bedc4c163f679a746f7ab7fb5d1fa : input.txt");
        tree.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
        tree.writeToFile();
        String hash = blob.hashStringToSHA1(tree.getContents());
        File f_tree = new File ("./objects/" + hash);
        assertTrue ("Tree does not write to correct SHA file", f_tree.exists());
        // assertTrue ("Tree Sha file does not contain the correct information", Utils.readFileToString("./objects/" + hash).equals(tree.getContents()));
    }
}