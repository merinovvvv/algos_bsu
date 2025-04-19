import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader reader;
    static BufferedWriter writer;
    static StringTokenizer tokenizer;

    static int citiesNum, roadsNum, quakesNum;
    static int components;
    static int[] parent, rank;
    static List<int[]> roads;
    static int[] quakes;
    static boolean[] isDestroyed;

    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new FileReader("input.txt"));
        writer = new BufferedWriter(new FileWriter("output.txt"));
        tokenizer = new StringTokenizer(reader.readLine());

        citiesNum = Integer.parseInt(tokenizer.nextToken());
        components = citiesNum;
        roadsNum = Integer.parseInt(tokenizer.nextToken());
        quakesNum = Integer.parseInt(tokenizer.nextToken());

        parent = new int[citiesNum + 1];
        rank = new int[citiesNum + 1];

        roads = new ArrayList<>(roadsNum);
        for (int i = 0; i < roadsNum; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int u = Integer.parseInt(tokenizer.nextToken());
            int v = Integer.parseInt(tokenizer.nextToken());
            roads.add(new int[]{u, v});
        }

        quakes = new int[quakesNum];
        isDestroyed = new boolean[roadsNum];
        for (int i = 0; i < quakesNum; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            quakes[i] = Integer.parseInt(tokenizer.nextToken()) - 1;
            isDestroyed[quakes[i]] = true;
        }

        for (int i = 1; i <= citiesNum; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        for (int i = 0; i < roadsNum; i++) {
            if (!isDestroyed[i]) {
                int u = roads.get(i)[0];
                int v = roads.get(i)[1];
                union(u, v);
            }
        }

        int[] result = new int[quakesNum];
        for (int i = quakesNum - 1; i >= 0; i--) {
            if (components == 1) {
                result[i] = 1;
            } else {
                result[i] = 0;
            }

            int index = quakes[i];
            int u = roads.get(index)[0];
            int v = roads.get(index)[1];
            union(u, v);
        }

        for (int i = 0; i < quakesNum; i++) {
            writer.write(String.valueOf(result[i]));
        }

        reader.close();
        writer.close();
    }

    static void union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        if (aRoot != bRoot) {
            if (rank[aRoot] < rank[bRoot]) {
                parent[aRoot] = bRoot;
            } else if (rank[aRoot] > rank[bRoot]) {
                parent[bRoot] = aRoot;
            } else {
                parent[aRoot] = bRoot;
                rank[bRoot]++;
            }
            components--;
        }
    }

    static int findSet(int a) {
        if (parent[a] != a) {
            parent[a] = findSet(parent[parent[a]]);
        }
        return parent[a];
    }
}