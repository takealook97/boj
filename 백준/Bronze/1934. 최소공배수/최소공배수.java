import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] a = new int[T];
        int[] b = new int[T];
        int[] c = new int[T];
        for (int i = 0; i < T; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
            c[i] = gcd(a[i],b[i]);
            System.out.println(a[i] * b[i] / c[i]);
        }
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}