import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.in"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int finish = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<int[]>> edges = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.get(u).add(new int[]{v, w});
            edges.get(v).add(new int[]{u, w});
        }

        int[] prev = new int[n + 1];
        long[] dist = new long[n + 1];
        boolean[] processed = new boolean[n + 1];
        PriorityQueue<long[]> minHeap = new PriorityQueue<>(
                Comparator.comparingLong(a -> a[1])
        );
        Arrays.fill(dist, Long.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;
        minHeap.add(new long[]{start, 0});
        dijkstra(start, finish, prev, dist, processed, edges, minHeap);
        ArrayList<Integer> path = restorePath(dist, prev, start, finish);
        Collections.reverse(path);

        bufferedWriter.write(dist[finish] + "\n");
        for (int i = 0; i < path.size(); i++) {
            bufferedWriter.write(path.get(i) + " ");
        }
        bufferedWriter.close();
    }

    static void dijkstra(int start, int finish, int[] prev, long[] dist, boolean[] processed, ArrayList<ArrayList<int[]>> edges, PriorityQueue<long[]> minHeap) {
        while (!minHeap.isEmpty()) {
            long[] top = minHeap.poll();
            int v = (int) top[0];
            int degV = edges.get(v).size();
            long dv = top[1];

            if (!processed[v]) {
                processed[v] = true;
                for (int[] edge : edges.get(v)) {
                    int u = edge[0];
                    int w = edge[1];
                    if (v != start && v != finish) {
                        if (!processed[u] && dv + w + degV < dist[u]) {
                            dist[u] = dv + w + degV;
                            prev[u] = v;
                            minHeap.add(new long[]{u, dist[u]});
                        }
                    } else {
                        if (!processed[u] && dv + w < dist[u]) {
                            dist[u] = dv + w;
                            prev[u] = v;
                            minHeap.add(new long[]{u, dist[u]});
                        }
                    }
                }
            }
        }
    }

    static ArrayList<Integer> restorePath(long[] dist, int[] prev, int start, int finish) {
        ArrayList<Integer> path = new ArrayList<>(dist.length);
        int current = finish;
        while (current != start) {
            path.add(current);
            current = prev[current];
        }
        path.add(start);
        return path;
    }
}