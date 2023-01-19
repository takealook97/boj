import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        long[] arr = new long[N];
        long[] check = new long[M];
        long count = 0;
        arr[0] = sc.nextLong();
        for (int i = 1; i < N; i++) {
            arr[i] = arr[i - 1] + sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            int remain = (int) (arr[i] % M);
            if (remain == 0) count++;
            check[remain]++;
        }
        for (int i = 0; i < M; i++) {
            if (check[i] > 1) {
                count = count + (check[i] * (check[i] - 1) / 2);
            }
        }
        System.out.println(count);
    }
}
