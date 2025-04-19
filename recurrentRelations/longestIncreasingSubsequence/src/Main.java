import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);

        int n = sc.nextInt();
        int[] string = new int[n];
        for (int i = 0; i < n; i++) {
            string[i] = sc.nextInt();
        }

        ArrayList<Integer> lis = new ArrayList<>();
        lis.add(string[0]);
        for (int i = 1; i < n; i++) {
            if (string[i] > lis.get(lis.size() - 1)) {
                lis.add(string[i]);
            } else {
                int index = upperBound(lis, string[i]);
                if (index == 0) {
                    lis.set(0, string[i]);
                } else if (lis.get(index-1) != string[i]) {
                    lis.set(index, string[i]);
                }
            }
        }

        FileWriter outputWriter = new FileWriter("output.txt");
        outputWriter.write(String.valueOf(lis.size()));
        outputWriter.close();

    }

    static int upperBound(ArrayList<Integer> string, int x) {
        int first = 0;
        int last = string.size();

        while (first < last) {
            int mid = (first + last) / 2;
            if (x < string.get(mid)) {
                last = mid;
            } else {
                first = mid + 1;
            }
        }
        return first;
    }
}