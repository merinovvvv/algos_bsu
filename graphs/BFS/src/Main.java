import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
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
        Queue<Integer> bfsQueue = new LinkedList<>();
        int[] marks = new int[n];
        boolean[] visited = new boolean[n + 1];

        bfs(bfsQueue, visited, marks, matrix);

        for (int i = 0; i < n; i++) {
            fileWriter.write(marks[i] + " ");
        }
        fileWriter.close();
    }

    static void bfs(Queue<Integer> bfsQueue, boolean[] visited, int[] marks, boolean[][] matrix) {

        int index = 1;

        for (int i = 1; i <= marks.length; i++) {
            if (!visited[i]) {
                marks[i - 1] = index;
                index++;
            }

            bfsQueue.add(i);
            visited[i] = true;

            while (!bfsQueue.isEmpty()) {
                int v = bfsQueue.remove();

                for (int u = 1; u <= marks.length; u++) {
                    if (matrix[v][u] && !visited[u]) {
                        visited[u] = true;
                        marks[u - 1] = index;
                        index++;
                        bfsQueue.add(u);
                    }
                }
            }

        }
    }
}