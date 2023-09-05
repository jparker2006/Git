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
        StringBuilder fileInfo = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            fileInfo.append(line).append("\n");
        }
        reader.close();
        String hashed = hashStringToSHA1(fileInfo.toString());
        writeCode(hashed, fileInfo);

    }
    

 public void read(String hashed, String text) throws IOException {
    FileWriter kev = new FileWriter(hashed);
        try (PrintWriter writer = new PrintWriter(kev)) {
            writer.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //This is from google, as allowed
    public static String hashStringToSHA1(String input) {
        try {
            MessageDigest sha1Digest = MessageDigest.getInstance("SHA-1");
            byte[] inputBytes = input.getBytes();
            sha1Digest.update(inputBytes);
            byte[] hashBytes = sha1Digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xFF & hashByte);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

public static void main(String[] args) {
    try {
        blob rw = new blob("input.txt");
    } catch (IOException e) {
        e.printStackTrace();
    }}
}