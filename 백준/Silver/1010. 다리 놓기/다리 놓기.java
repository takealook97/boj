import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] N = new int[T];
        int[] M = new int[T];
        for (int i = 0; i < T; i++) {
            N[i] = sc.nextInt();
            M[i] = sc.nextInt();
            if (M[i] - N[i] <= N[i]) {
                N[i] = M[i] - N[i];
            }
            long a = 1;
            long b = 1;
            for (int j = 0; j < N[i]; j++) {
                a *= (M[i] - j);
                b *= (N[i] - j);
            }
            System.out.println(a / b);
        }
    }
}