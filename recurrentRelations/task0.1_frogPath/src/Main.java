import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] mosquitoes = new int[n];

        for (int i = 0; i < n; i++) {
            mosquitoes[i] = sc.nextInt();
        }

        int[] maxMosquitoes = new int[n];
        maxMosquitoes[0] = mosquitoes[0];
        if (n > 1) {
            maxMosquitoes[1] = Integer.MIN_VALUE;
            for (int i = 2; i < n; i++) {
                maxMosquitoes[i] = Math.max(maxMosquitoes[i - 2], maxMosquitoes[Math.max(i - 3, 0)]) + mosquitoes[i];
            }
        }

        ArrayList<Integer> indexes = new ArrayList<>(n/2);
        indexes.add(n - 1);
        for (int i = n - 1; i >= 2; ) {
            if (maxMosquitoes[i] == maxMosquitoes[i-2] + mosquitoes[i]) {
                i -= 2;
            } else {
                i -= 3;
            }
            indexes.add(i);
        }

        if (maxMosquitoes[n-1] > 0) {
            System.out.println(maxMosquitoes[n-1]);
            for (int i = indexes.size() - 1; i >= 0; i--) {
                System.out.print(indexes.get(i) + 1 + " ");
            }
        } else {
            System.out.println(-1);
        }
    }
}