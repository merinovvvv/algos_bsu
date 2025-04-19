import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        Set<Integer> nums = new HashSet<>();
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);

        while (sc.hasNextInt()) {
            nums.add(sc.nextInt());
        }
        sc.close();

        long sum = 0;

        for (int num : nums) {
            sum += num;
        }

        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(Long.toString(sum));
        fileWriter.close();
    }
}