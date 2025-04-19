import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        FileWriter fileWriter = new FileWriter("output.txt");
        int m = sc.nextInt();
        int c = sc.nextInt();
        int n = sc.nextInt();

        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            linkedHashSet.add(sc.nextInt());
        }

        List<Integer> uniqueList = new ArrayList<>(linkedHashSet);


        int[] hashTable = new int[m];
        Arrays.fill(hashTable, -1);

        for (int j = 0; j < uniqueList.size(); j++) {
            int i = 0;
            int key = uniqueList.get(j);
            boolean inserted = false;
            while (!inserted && i < m) {
                int index = ((key % m) + c * i) % m;
                if (hashTable[index] == -1) {
                    hashTable[index] = key;
                    inserted = true;
                } else {
                    i++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            fileWriter.write(hashTable[i] + " ");
        }
        fileWriter.close();
    }
}