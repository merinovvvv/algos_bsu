import java.util.Scanner;

public class Main {

    static final long MOD = 1000000007L;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        long result = (factorial(n)
                * binPow(factorial(k), MOD - 2) % MOD
                * binPow(factorial(n-k), MOD - 2) % MOD) % MOD;
        System.out.println(result);

    }

    static long factorial(int t) {
        long res = 1;
        for (int i = 2; i <= t; i++) {
            res = (res * i) % MOD;
        }
        return res;
    }

    static long binPow(long b, long n) {
        if (n==0)
            return 1;
        if (n==1)
            return b % MOD;
        long t = binPow(b, n >> 1);
        if (n % 2 == 0) {
            return (t * t) % MOD;
        }
        return ((t * t) % MOD * b) % MOD;
    }
}