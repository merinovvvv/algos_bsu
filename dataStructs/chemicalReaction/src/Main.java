import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("in.txt"));
        FileWriter fileWriter = new FileWriter("out.txt");
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] matrix = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        Stack<Integer> bottle = new Stack<>();
        bottle.push(sc.nextInt());
        for (int k = 1; k < m; k++) {
            int i = sc.nextInt();
            int j = bottle.pop();
            while (true) {
                if (matrix[i][j] == 0) {
                    bottle.push(j);
                    break;
                }
                i = matrix[i][j];
                if (bottle.isEmpty()) {
                    break;
                }
                j = bottle.pop();
            }
            bottle.push(i);
        }

        fileWriter.write(String.valueOf(bottle.pop()));
        while (!bottle.isEmpty()) {
            fileWriter.write(" " + bottle.pop());
        }
        fileWriter.close();
    }
}