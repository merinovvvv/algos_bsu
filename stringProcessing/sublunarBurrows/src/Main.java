import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] strings = new String[n];
        int counter = 0;
        for (int i = 0; i < n; i++) {
            strings[i] = reader.readLine();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isPalindrome(strings[i] + strings[j]) && i != j) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    static boolean isPalindrome(String string) {
        int i = 0;
        int j = string.length() - 1;
        while (i < j) {
            if (string.charAt(i) != string.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}