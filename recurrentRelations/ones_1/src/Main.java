import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        long[][] mas = new long[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            mas[i][i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            mas[i][0] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i-1; j++) {
                mas[i][j] = ((mas[i-1][j-1]) + (mas[i-1][j])) % 1000000007L;
            }
        }
        System.out.println(mas[n][k]);
    }
}