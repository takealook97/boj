import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int k = sc.nextInt() + 1;
            int n = sc.nextInt();
            int[] num = new int[k * n + 1];

            for (int j = 1; j <= k * n; j++) {
                num[0] = 0;
                for (int l = 1; l <= n; l++) {
                    num[l] = l;
                }
                for (int l = 0; l < k; l++) {
                    num[1 + l * n] = 1;
                }
                if (j > n && k > 0 && n > 1) {
                    num[j] = num[j - 1] + num[j - n];
                }
            }
            System.out.println(num[k * n]);
        }
    }
}
