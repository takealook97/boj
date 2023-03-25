import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] set = new int[M];
        int[] each = new int[M];
        for (int i = 0; i < M; i++) {
            set[i] = sc.nextInt();
            each[i] = sc.nextInt();
        }
        Arrays.sort(set);
        Arrays.sort(each);
        int a = Math.min(set[0], each[0] * 6);
        int b = each[0];
        int c = Math.min(a, (N % 6) * b);
        System.out.println((N / 6) * a + c);
    }
}
