import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int S = sc.nextInt();
        int K = sc.nextInt();
        int x = S / K;
        int y = S % K;
        int i = 0;
        int[] arr = new int[K];
        while (y > 0) {
            arr[i] = x + 1;
            i++;
            y--;
        }
        for (int j = 0; j < K; j++) {
            if (arr[j] == 0) arr[j] = x;
        }
        long result = 1;
        for (int j = 0; j < K; j++) {
            result *= arr[j];
        }
        System.out.println(result);
    }
}
