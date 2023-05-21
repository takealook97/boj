import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int MAX = 1000000;
    static boolean[] prime;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        getPrime();
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            int count = 0;
            boolean check = !prime[N / 2];
            for (int i = 2; i <= N; i++) {
                if (!prime[i] && !prime[N - i]) {
                    count++;
                }
            }
            count /= 2;
            if(check) count++;
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }

    static void getPrime() {
        prime = new boolean[MAX + 1];
        prime[0] = prime[1] = true;
        for (int i = 2; i < Math.sqrt(MAX); i++) {
            for (int j = i * i; j <= MAX; j += i) {
                prime[j] = true;
            }
        }
    }
}