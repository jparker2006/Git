import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class blob {
    
    public blob(String inputFile) throws IOException {
        File file = new File(inputFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String fileInfo;
        while ((line = reader.readLine()) != null) {
            fileInfo += line;
        }
        reader.close();
        String hashed = encryptThisString(fileInfo);
        writeCode(hashed, fileInfo);

    }
    

 public void writeCode(String hashed, String text) throws IOException {
    FileWriter kev = new FileWriter(hashed);
        try (PrintWriter writer = new PrintWriter(kev)) {
            writer.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //This is from google, as allowed
    public static String encryptThisString(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());
            Integer no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
}

public static void main(String[] args) {
    try {
        blob rw = new blob("input.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }}
}