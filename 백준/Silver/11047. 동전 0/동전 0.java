import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (A[N - 1 - i] <= K) {
                K -= A[N - 1 - i];
                count++;
                i = -1;
            }
            if (K == 0) {
                break;
            }
        }
        System.out.println(count);
    }
}