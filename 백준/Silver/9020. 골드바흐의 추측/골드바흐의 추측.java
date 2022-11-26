import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            boolean[] prime = new boolean[n + 1];
            prime[0] = prime[1] = true;
            for (int j = 0; j <= Math.sqrt(n + 1); j++) {
                if (!prime[j]) {
                    for (int k = j * j; k <= n; k += j) {
                        prime[k] = true;
                    }
                }
            }
            int a = 0;
            int b = 0;
            for (int j = 0; j <= n; j++) {
                if (!prime[j] && !prime[n - j]) {//j가 소수일때
                    if (n - 2 * j >= 0 && n - j >= j) {
                        a = j;
                        b = n - j;
                    }
                }
            }
            System.out.println(a + " " + b);
        }
    }
}