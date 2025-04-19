import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader;
    static BufferedWriter writer;
    static StringTokenizer tokenizer;

    static int cities;
    static int freeCities;
    static int requests;

    static int[] parent;
    static int[] vertexesNum;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        writer = new BufferedWriter(new FileWriter("output.txt"));
        tokenizer = new StringTokenizer(reader.readLine());

        cities = Integer.parseInt(tokenizer.nextToken());
        freeCities = cities;
        requests = Integer.parseInt(tokenizer.nextToken());

        parent = new int[cities + 1];
        vertexesNum = new int[cities + 1];

        for (int i = 1; i <= cities; i++) {
            parent[i] = i;
            vertexesNum[i] = 1;
        }

        for (int i = 1; i <= requests; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            union(a, b);
            writer.write(freeCities + "\n");
        }
        reader.close();
        writer.close();
    }

    static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot != bRoot) {
            if (vertexesNum[aRoot] < vertexesNum[bRoot]) {
                parent[aRoot] = bRoot;
            } else if (vertexesNum[aRoot] > vertexesNum[bRoot]) {
                parent[bRoot] = aRoot;
            } else {
                parent[aRoot] = bRoot;
                vertexesNum[bRoot]++;
            }
            freeCities--;
        }
    }

    static int findSet(int a) {
        if (parent[a] != a) {
            parent[a] = findSet(parent[parent[a]]);
        }
        return parent[a];
    }
}