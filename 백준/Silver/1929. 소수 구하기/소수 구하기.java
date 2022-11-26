import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int A = N - M + 1;
        boolean[] prime = new boolean[N + 1];
        prime[0] = prime[1] = true;
        for (int i = 0; i < Math.sqrt(N + 1); i++) {
            if (!prime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    prime[j] = true;
                }
            }
        }
        for (int i = M; i <= N; i++) {
            if (!prime[i]) {
                System.out.println(i);
            }
        }
    }
}
