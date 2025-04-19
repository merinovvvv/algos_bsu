import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        FileWriter outputWriter = new FileWriter("output.txt");

        Scanner sc = new Scanner(inputFile);
        String s = sc.nextLine();
        int n = s.length();
        int[][] p = new int[n][n];
        for (int i = 0; i < n; i++) {
            p[i][i] = 1;
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                p[i][i+1] = 2;
            } else {
                p[i][i+1] = 1;
            }
        }

        for (int j = 2; j < n; j++) {
            for (int i = j - 2; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    p[i][j] = p[i+1][j-1] + 2;
                } else {
                    p[i][j] = Math.max(p[i+1][j], p[i][j-1]);
                }
            }
        }
        int pLen = p[0][n-1];

        outputWriter.write(pLen + "\n");

        String palindrome = getPalindrome(n, s, p);
        outputWriter.write(palindrome);
        outputWriter.close();
    }

    private static String getPalindrome(int n, String s, int[][] p) {
        int i = 0;
        int j = n - 1;

        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();

        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                left.append(s.charAt(i));
                if (i != j) {
                    right.insert(0, s.charAt(j));
                }
                i++;
                j--;
            } else if (p[i+1][j] > p[i][j-1]) {
                i++;
            } else {
                j--;
            }
        }

        return left.toString() + right;
    }
}