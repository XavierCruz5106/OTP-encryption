import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Main {

  private static void encrypt(File file, File encryptedFile, File keys) throws IOException {
    SecureRandom secureRandom = new SecureRandom();
    Scanner input = new Scanner(file);
    BufferedWriter bw = new BufferedWriter(new FileWriter(encryptedFile, false));
    BufferedWriter bw2 = new BufferedWriter(new FileWriter(keys, false));

    while (input.hasNextLine()) {
        String line = input.nextLine();

        for (int i = 0; i < line.length(); i++) {
            int c = line.charAt(i);
            int key = secureRandom.nextInt(256) * secureRandom.nextInt(5);
            int encryptedChar = c ^ key;

            bw.write(encryptedChar + " ");
            bw2.write(key + " ");
        }
        bw.write("\n");
        bw2.write("\n");
    }
    bw.close();
    bw2.close();
    input.close();
  }



    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Please supply the file you want encrypted");
            return;
        }

        String fileName = args[0];
        File file = new File(fileName);
        File encryptedFile = new File("encrypted_file");
        File keys = new File("keys");

        encrypt(file, encryptedFile, keys);
    }
}