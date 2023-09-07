import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;
import java.io.*;

public class index extends blob {

    private HashMap<String, Double> files;

    public static void init() throws IOException {
        String folder = "objects";

        java.nio.file.Path folderPath = Paths.get(folder);

        if (!Files.exists(folderPath)) {
            try {
                Files.createDirectory(folderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path index = folderPath.resolve("index");
        if (!Files.exists(index)) {
            try {
                Files.createFile(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Commit(String fileName) throws IOException {
        try {
            blobFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            String file = "input.txt";
            init();
            Commit("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}