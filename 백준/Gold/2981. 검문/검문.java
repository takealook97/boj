import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        int num = arr.get(1) - arr.get(0);
        for (int i = 1; i < N; i++) {
            num = gcd(num, arr.get(i) - arr.get(i - 1));
        }
        for (int i = 2; i <= num; i++) {
            if (num % i == 0) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        else return gcd(b, a % b);
    }
}