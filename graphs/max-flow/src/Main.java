import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int source;
        int target;
        int capacity;
        int flow;

        public Edge(int source, int target, int capacity) {
            this.source = source;
            this.target = target;
            this.capacity = capacity;
            this.flow = 0;
        }

        public int getResidual() {
            return capacity - flow;
        }
    }

    static List<List<Integer>> network;
    static List<Edge> flowEdges;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        network = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            network.add(new ArrayList<>());
        }
        flowEdges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            addEdge(u, v, w);
        }

        System.out.println(maxFlow(n));
    }

    private static void addEdge(int u, int v, int capacity) {
        network.get(u).add(flowEdges.size());
        flowEdges.add(new Edge(u, v, capacity));

        network.get(v).add(flowEdges.size());
        flowEdges.add(new Edge(v, u, 0));
    }

    private static int maxFlow(int t) {
        if (1 == t) {
            return 0;
        }
        int maxFlow = 0;

        while (true) {
            int[] pred = new int[n + 1];
            Arrays.fill(pred, -1);
            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            pred[1] = -2;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                for (int edgeIndex : network.get(u)) {
                    Edge e = flowEdges.get(edgeIndex);
                    if (pred[e.target] == -1 && e.getResidual() > 0) {
                        pred[e.target] = edgeIndex;
                        if (e.target == t) {
                            break;
                        }
                        queue.add(e.target);
                    }
                }
                if (pred[t] != -1) {
                    break;
                }
            }

            if (pred[t] == -1) {
                break;
            }

            int pathFlow = Integer.MAX_VALUE;
            for (int v = t; v != 1; ) {
                int edgeIndex = pred[v];
                Edge e = flowEdges.get(edgeIndex);
                pathFlow = Math.min(pathFlow, e.getResidual());
                v = e.source;
            }

            for (int v = t; v != 1; ) {
                int edgeIndex = pred[v];
                Edge e = flowEdges.get(edgeIndex);
                e.flow += pathFlow;
                Edge rev = flowEdges.get(edgeIndex ^ 1);
                rev.flow -= pathFlow;
                v = e.source;
            }

            maxFlow += pathFlow;
        }
        return maxFlow;
    }
}