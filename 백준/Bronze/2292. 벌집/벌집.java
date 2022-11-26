import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double N = sc.nextInt();
        int a = 1;
        int b = 1;
        for (int i = 0; i < N; i++) {
            a += 6 * i;
            b += 6 * (i + 1);
            if (N == 1) {
                System.out.println(1);
            }
            if (a <= N && N <= b && N != 1) {
                System.out.println(i + 2);
                break;
            }
        }
    }
}