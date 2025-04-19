import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File input = new File("input.txt");
        Scanner sc = new Scanner(input);
        FileWriter output = new FileWriter("output.txt");

        int n = sc.nextInt();
        long[] mas = new long[n];
        for (int i = 0; i < n; i++) {
            mas[i] = sc.nextLong();
        }

        sc.close();

        for (int i = 0; i < n; i++) {
            if ((2 * i + 1 < n && mas[i] > mas[2 * i + 1]) || (2 * i + 2 < n && mas[i] > mas[2 * i + 2])) {
                output.write("No");
                output.close();
                return;
            }
        }

        output.write("Yes");
        output.close();
    }
}