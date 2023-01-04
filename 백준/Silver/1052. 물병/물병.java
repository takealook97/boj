import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        if (N <= K) {
            System.out.println(0);
            return;
        }
        int result = 0;
        while (true) {
            int num = N;
            int count = 0;
            while (num != 0) {
                if (num % 2 != 0) {
                    count++;
                }
                num /= 2;
            }
            if (count <= K) break;
            N++;
            result++;
        }
        System.out.println(result);
    }
}
