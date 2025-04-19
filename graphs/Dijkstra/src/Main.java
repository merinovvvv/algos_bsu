import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> edges = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.get(u).add(new int[]{v, w});
            edges.get(v).add(new int[]{u, w});
        }

        long[] dist = new long[n + 1];
        boolean[] processed = new boolean[n + 1];
        PriorityQueue<long[]> minHeap = new PriorityQueue<>(
                Comparator.comparingLong(a -> a[1])
        );
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[1] = 0;
        minHeap.add(new long[]{1, 0});
        dijkstra(dist, processed, edges, minHeap);

        bufferedWriter.write(String.valueOf(dist[n]));
        bufferedWriter.close();
    }

    static void dijkstra(long[] dist, boolean[] processed, ArrayList<ArrayList<int[]>> edges, PriorityQueue<long[]> minHeap) {
        while (!minHeap.isEmpty()) {
            long[] top = minHeap.poll();
            int v = (int) top[0];
            long dv = top[1];

            if (!processed[v]) {
                processed[v] = true;
                for (int[] edge : edges.get(v)) {
                    int u = edge[0];
                    int w = edge[1];
                    if (!processed[u] && dv + w < dist[u]) {
                        dist[u] = dv + w;
                        minHeap.add(new long[]{u, dist[u]});
                    }
                }
            }
        }
    }
}