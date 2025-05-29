import java.util.*;
import java.io.*;

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return x == pair.x && y == pair.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

public class Main {
    static int best = 0;
    static ArrayList<Pair> current = new ArrayList<>();
    static ArrayList<ArrayList<Pair>> result = new ArrayList<>();
    static Map<Pair, Pair> edgeToCell; //connects graph's edges with coordinates on a chessboard

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int k = Integer.parseInt(firstLine[2]);

        boolean[][] table = new boolean[n][m]; //is barrier (true = black figure)
        for (int i = 0; i < k; i++) {
            String[] coords = reader.readLine().split(" ");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);
            table[x - 1][y - 1] = true;
        }
        reader.close();

        int numRows = 0;
        int[][] rowId = new int[n][m];
        for (int[] row : rowId) Arrays.fill(row, -1);
        for (int i = 0; i < n; i++) {
            int j = 0;
            while (j < m) {
                if (table[i][j]) {
                    j++;
                    continue;
                }
                while (j < m && !table[i][j]) {
                    rowId[i][j] = numRows;
                    j++;
                }
                numRows++;
            }
        }

        int numCols = 0;
        int[][] colId = new int[n][m];
        for (int[] col : colId) Arrays.fill(col, -1);
        for (int j = 0; j < m; j++) {
            int i = 0;
            while (i < n) {
                if (table[i][j]) {
                    i++;
                    continue;
                }
                while (i < n && !table[i][j]) {
                    colId[i][j] = numCols;
                    i++;
                }
                numCols++;
            }
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>(); //a bipartite graph
        edgeToCell = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!table[i][j]) {
                    int r = rowId[i][j];
                    int c = colId[i][j];
                    if (r != -1 && c != -1) {
                        graph.computeIfAbsent(r, _ -> new HashSet<>()).add(c);
                        edgeToCell.put(new Pair(r, c), new Pair(i, j));
                    }
                }
            }
        }

        //greedy
        int[] matchLeft = new int[numRows];
        Arrays.fill(matchLeft, -1);
        int[] matchRight = new int[numCols];
        Arrays.fill(matchRight, -1);
        boolean[] usedRight = new boolean[numCols];

        int greedy = 0;
        Arrays.fill(usedRight, false);
        for (int u : graph.keySet()) {
            for (int v : graph.get(u)) {
                if (!usedRight[v]) {
                    usedRight[v] = true;
                    greedy++;
                    break;
                }
            }
        }
        best = greedy;

        List<Integer> leftNodes = new ArrayList<>();
        for (int u = 0; u < numRows; u++) {
            if (graph.containsKey(u) && !graph.get(u).isEmpty()) {
                leftNodes.add(u);
            }
        }

        Arrays.fill(usedRight, false);
        dfs(graph, matchLeft, matchRight, usedRight, 0, leftNodes); //maximum matchings

        for (ArrayList<Pair> list : result) {
            for (Pair p : list) {
                writer.write((p.y + 1) + " " + (p.x + 1) + "\n");
            }
            writer.write("<--->\n");
        }
        writer.write(String.valueOf(result.size()));
        writer.close();
    }

    private static void dfs(Map<Integer, Set<Integer>> graph, int[] matchLeft, int[] matchRight, boolean[] usedRight, int idx, List<Integer> leftNodes) {
        if (leftNodes.size() - idx + current.size() < best) { //toCheck + checked < best
            return;
        }

        if (idx == leftNodes.size()) {
            int matched = current.size();
            if (matched == best) {
                result.add(new ArrayList<>(current));
            } else if (matched > best) {
                result.clear();
                result.add(new ArrayList<>(current));
                best = matched;
            }
            return;
        }

        int u = leftNodes.get(idx);
        Set<Integer> adj = graph.getOrDefault(u, Collections.emptySet());
        for (int v : adj) {
            if (usedRight[v]) continue;

            int prevMatchLeft = matchLeft[u];
            int prevMatchRight = matchRight[v];
            boolean prevUsedRight = usedRight[v];

            matchLeft[u] = v;
            matchRight[v] = u;
            usedRight[v] = true;
            Pair cell = edgeToCell.get(new Pair(u, v));
            current.add(cell);

            dfs(graph, matchLeft, matchRight, usedRight, idx + 1, leftNodes);

            matchLeft[u] = prevMatchLeft;
            matchRight[v] = prevMatchRight;
            usedRight[v] = prevUsedRight;
            current.remove(current.size() - 1);
        }

        dfs(graph, matchLeft, matchRight, usedRight, idx + 1, leftNodes);
    }
}