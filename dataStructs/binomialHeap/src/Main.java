import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);
        FileWriter output = new FileWriter("output.txt");
        long n = sc.nextLong();
        String nBinaryReversed = new StringBuilder(Long.toBinaryString(n)).reverse().toString();
        for (int i = 0; i < nBinaryReversed.length(); i++) {
            if (nBinaryReversed.charAt(i) == '1') {
                output.write(i + "\n");
            }
        }
        output.close();
    }
}