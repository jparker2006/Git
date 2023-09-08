import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Files;
import java.io.BufferedWriter;
import java.io.IOException;

public class index extends blob {

    public static HashMap<String, String> files;

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

                for (String orginalFile : files.keySet()) {
                    String hashFileName = files.get(orginalFile);
                    files.put(orginalFile, hashFileName);
                }

                try (BufferedWriter writer = Files.newBufferedWriter(index, StandardOpenOption.CREATE,
                        StandardOpenOption.WRITE)) {
                    for (Map.Entry<String, String> entry : files.entrySet()) {
                        writer.write(entry.getKey() + " : " + entry.getValue());
                        writer.newLine();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

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

    public static void remove(String fileName) throws IOException {
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