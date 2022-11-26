import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N = sc.nextInt();
            if (N == 0) {
                break;
            }
            boolean[] prime = new boolean[2 * N + 1];
            prime[0] = prime[1] = true;

            for (int i = 0; i <= Math.sqrt(2 * N); i++) {
                if (!prime[i]) {
                    for (int j = i * i; j <= 2 * N; j += i) {
                        prime[j] = true;
                    }
                }
            }

            int cnt = 0;
            for (int i = N + 1; i <= 2 * N; i++) {
                if (!prime[i]) {
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
}