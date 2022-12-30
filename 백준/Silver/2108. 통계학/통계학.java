import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        double sum = 0;
        int[] check = new int[8001];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= 8000; j++) {
                if (arr[i] == j - 4000) {
                    check[j]++;
                    break;
                }
            }
        }
        int mode = 0;
        for (int i = 0; i <= 8000; i++) {
            if (mode < check[i]) {
                mode = check[i];
            }
        }
        int a = 0;
        out:
        for (int i = 0; i <= 8000; i++) {
            if (check[i] == mode) {
                a = i - 4000;
                for (int j = i + 1; j <= 8000; j++) {
                    if (check[j] == mode) {
                        a = j - 4000;
                        break out;
                    }
                }
            }
        }
        if (N == 1) {
            a = arr[0];
        }

        if (sum / N <= 0 && sum / N > -0.5) {
            System.out.println(0);
        } else System.out.println(String.format("%.0f", sum / N));
        System.out.println(arr[(N - 1) / 2]);
        System.out.println(a);
        System.out.println(arr[N - 1] - arr[0]);
    }
}