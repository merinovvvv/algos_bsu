import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[][] mas = new int[n+1][n+1];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt();
        }

        for (int i = 0; i <= n; i++) {
            mas[i][0] = 0;
            mas[0][i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i-1] == b[j-1]) {
                    mas[i][j] = mas[i-1][j-1] + 1;
                } else {
                    mas[i][j] = Math.max(mas[i-1][j], mas[i][j-1]);
                }
            }
        }
        System.out.println(mas[n][n]);

        int iIndex = n;
        int jIndex = n;
        ArrayList<Integer> iIndexes = new ArrayList<>(mas[n][n]);
        ArrayList<Integer> jIndexes = new ArrayList<>(mas[n][n]);

        while (mas[iIndex][jIndex] != 0) {
            if (a[iIndex - 1] == b[jIndex - 1]) {
                iIndex -= 1;
                jIndex -= 1;

                iIndexes.add(iIndex);
                jIndexes.add(jIndex);
            } else {
                if (mas[iIndex][jIndex] == mas[iIndex-1][jIndex]) {
                    iIndex-=1;
                } else {
                    jIndex-=1;
                }
            }
        }

        for (int i = iIndexes.size() - 1; i >= 0; i--) {
            System.out.print(iIndexes.get(i) + " ");
        }
        System.out.println();
        for (int j = jIndexes.size() - 1; j >= 0; j--) {
            System.out.print(jIndexes.get(j) + " ");
        }
    }
}