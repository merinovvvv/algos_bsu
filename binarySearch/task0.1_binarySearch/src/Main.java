import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        ArrayList<Integer> nums = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            nums.add(sc.nextInt());
        }

        int k = sc.nextInt();
        ArrayList<Integer> asks = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            asks.add(sc.nextInt());
        }

        for (Integer x : asks) {
            int first = 0;
            int last = n;
            boolean b = false;

            while (first < last) {
                int mid = (first + last) / 2;
                if (x < nums.get(mid)) {
                    last = mid;
                } else if (x > nums.get(mid)) {
                    first = mid + 1;
                } else {
                    b = true;
                    break;
                }
            }
            int l = findL(nums, x);
            int r = findR(nums, x);

            System.out.println((b ? 1 : 0) + " " + l + " " + r);
        }
    }

    static int findL(ArrayList<Integer> nums, int x) { // <=
        int first = 0;
        int last = nums.size();

        while (first < last) {
            int mid = (first + last) / 2;
            if (x <= nums.get(mid)) {
                last = mid;
            } else {
                first = mid + 1;
            }
        }
        return first;
    }

    static int findR(ArrayList<Integer> nums, int x) {
        int first = 0;
        int last = nums.size();

        while (first < last) {
            int mid = (first + last) / 2;
            if (x < nums.get(mid)) {
                last = mid;
            } else {
                first = mid + 1;
            }
        }
        return first;
    }
}