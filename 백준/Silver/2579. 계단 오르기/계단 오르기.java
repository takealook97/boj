import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr;
    static Integer[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new Integer[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        
        if (N == 1) {
            System.out.println(arr[1]);
            System.exit(0);
        } else if (N == 2) {
            System.out.println(arr[1] + arr[2]);
            System.exit(0);
        }
        
        dp[0] = arr[0];
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        System.out.println(find(N));
    }

    static int find(int N) {
        if (dp[N] == null) {
            dp[N] = Math.max(find(N - 2), find(N - 3) + arr[N - 1]) + arr[N];
        }
        return dp[N];
    }
}
