import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);
        int s = sc.nextInt();

        int[] n = new int[s];
        int[] m = new int[s];

        int index = 0;

        while (sc.hasNext()) {
            n[index] = sc.nextInt();
            m[index] = sc.nextInt();
            index++;
        }

        int[][] mas = new int[s][s];

        for (int i = 0; i < s - 1; i++) {
            mas[i][i+1] = n[i] * m[i] * m[i+1];
        }

        for (int j = 1; j < s; j++) {
            for (int i = j - 1; i >= 0; i--) {
                for (int k = i; k < j; k++) {
                    int min = mas[i][k] + mas[k+1][j] + n[i]*m[k]*m[j];
                    if (mas[i][j] == 0) {
                        mas[i][j] = min;
                    } else {
                        if (min < mas[i][j]) {
                            mas[i][j] = min;
                        }
                    }
                }

            }
        }
        FileWriter outputWriter = new FileWriter("output.txt");
        outputWriter.write(String.valueOf(mas[0][s-1]));
        outputWriter.close();
    }
}