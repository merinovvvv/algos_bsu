import java.util.Objects;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] a = new long[n];
        int k = (int) Math.sqrt(n) + 1;
        long[] b = new long[k];

        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        for (int i = 0; i < k; i++) {
            long sum = 0;
            for (int j = i * k; j < Math.min((i + 1) * k, n); j++) {
                sum += a[j];
            }
            b[i] = sum;
        }

        int q = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < q; i++) {
            StringTokenizer tokenizer = new StringTokenizer(sc.nextLine());
            String query = tokenizer.nextToken();
            if (Objects.equals(query, "Add")) {
                int index = Integer.parseInt(tokenizer.nextToken());
                int x = Integer.parseInt(tokenizer.nextToken());
                a[index] += x;
                b[index / k] += x;
            } else if (Objects.equals(query, "FindSum")) {
                int l = Integer.parseInt(tokenizer.nextToken());
                int r = Integer.parseInt(tokenizer.nextToken());
                int jl = l / k;
                int jr = r / k;
                long sum = 0;
                if (jl == jr) {
                    for (int j = l; j < r; j++) {
                        sum += a[j];
                    }
                } else {
                    for (int j = l; j < (jl + 1) * k; j++) {
                        sum += a[j];
                    }
                    for (int j = jl + 1; j < jr; j++) {
                        sum += b[j];
                    }
                    for (int j = jr * k; j < r; j++) {
                        sum += a[j];
                    }
                }
                System.out.println(sum);
            }
        }
    }
}