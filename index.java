import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;


public class index extends blob{
    
    public static void init() throws IOException {
        String folder = "objects";

        java.nio.file.Path folderPath = Paths.get(folder);

        if (!Files.exists(folderPath))
        {
            try {
                Files.createDirectory(folderPath);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }   
        Path index = folderPath.resolve("index");
        if(!Files.exists(index))
        {
            try{
                Files.createFile(index);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    

    public static void write(String hashed, StringBuilder inside)
    {
        try{
        String newFile = hashed;
        FileWriter write = new FileWriter(newFile);
        write.write(inside.toString());
        write.close();
    }
   catch (IOException e)
    {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    try {String file = "input.txt";
    blobFile(file);
} catch (IOException e)
{
    e.printStackTrace();
}
}
}