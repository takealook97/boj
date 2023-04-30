import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int k = sc.nextInt();
        int start = 1;
        int end = k;
        int result = 0;
        while (start <= end) {
            int middle = (start + end) / 2;
            int count = 0;
            for (int i = 1; i <= N; i++) {
                count += Math.min(middle / i, N);
            }
            if (count < k) {
                start = middle + 1;
            } else {
                result = middle;
                end = middle - 1;
            }
        }
        System.out.println(result);
    }
}