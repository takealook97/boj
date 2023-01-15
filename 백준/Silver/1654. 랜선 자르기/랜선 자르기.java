import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        long[] arr = new long[K];
        long max = 0;
        for (int i = 0; i < K; i++) {
            arr[i] = sc.nextLong();
            max = Math.max(arr[i], max);
        }
        max++;
        long min = 0;
        long mid = 0;
        while (min < max) {
            long count = 0;
            mid = (max + min) / 2;
            for (int i = 0; i < K; i++) {
                count += arr[i] / mid;
            }
            if (count < N) {
                max = mid;
            } else min = mid + 1;
        }
        System.out.println(min - 1);
    }
}