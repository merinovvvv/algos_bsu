import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("bst.in"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("bst.out"));

        int n = Integer.parseInt(reader.readLine());

        long[] keyValue = new long[n];
        long[] lowerBound = new long[n];
        long[] upperBound = new long[n];

        String[] firstLine = reader.readLine().split(" ");
        keyValue[0] = Long.parseLong(firstLine[0]);
        lowerBound[0] = Long.MIN_VALUE;
        upperBound[0] = Long.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            keyValue[i] = Long.parseLong(line[0]);
            int parent = Integer.parseInt(line[1]) - 1;
            String pos = line[2];

            if (pos.equals("L")) {
                upperBound[i] = keyValue[parent];
                lowerBound[i] = lowerBound[parent];
            } else {
                lowerBound[i] = keyValue[parent];
                upperBound[i] = upperBound[parent];
            }

            if (!(keyValue[i] >= lowerBound[i] && keyValue[i] < upperBound[i])) {
                writer.write("NO");
                writer.close();
                reader.close();
                return;
            }
        }

        writer.write("YES");
        writer.close();
        reader.close();
    }
}