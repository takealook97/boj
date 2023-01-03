import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[N + 1];
        arr[0] = 0;
        for (int i = 0; i < N; i++) {
            arr[i + 1] = arr[i] + sc.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(arr[b] - arr[a - 1]);
        }
    }
}