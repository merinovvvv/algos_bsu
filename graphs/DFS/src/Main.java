import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int index = 1;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fileWriter = new FileWriter("output.txt");

        int n = sc.nextInt();
        boolean[][] matrix = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = sc.nextInt() == 1;
            }
        }

        int[] marks = new int[n];
        boolean[] visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i, marks, visited, matrix);
            }
        }

        for (int i = 0; i < n; i++) {
            fileWriter.write(marks[i] + " ");
        }
        fileWriter.close();
    }

    static void dfs(int v, int[] marks, boolean[] visited, boolean[][] matrix) {

        visited[v] = true;
        marks[v - 1] = index;
        index++;

        for (int u = 1; u <= marks.length; u++) {
            if (matrix[v][u] && !visited[u]) {
                dfs(u, marks, visited, matrix);
            }
        }
    }
}