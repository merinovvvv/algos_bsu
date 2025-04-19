import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("in.txt");
        Scanner sc = new Scanner(inputFile);

        int x = sc.nextInt();
        int y = sc.nextInt();
        int z = sc.nextInt();

        sc.nextLine();

        String A = sc.nextLine();
        String B = sc.nextLine();

        int aLength = A.length();
        int bLength = B.length();

        int[][] operations = new int[aLength + 1][bLength + 1];
        operations[0][0] = 0;

        for (int i = 1; i <= aLength; i++) {
            operations[i][0] = i * x;
        }

        for (int j = 1; j <= bLength; j++) {
            operations[0][j] = j * y;
        }

        for (int i = 1; i <= aLength; i++) {
            for (int j = 1; j <= bLength; j++) {
                operations[i][j] = Math.min(
                        Math.min(
                                operations[i-1][j] + x,
                                operations[i][j-1] + y
                        ),
                        operations[i-1][j-1] + (A.charAt(i-1) == B.charAt(j-1) ? 0 : 1) * z
                );
            }
        }

        FileWriter outWriter = new FileWriter("out.txt");
        outWriter.write(String.valueOf(operations[aLength][bLength]));
        outWriter.close();
    }
}