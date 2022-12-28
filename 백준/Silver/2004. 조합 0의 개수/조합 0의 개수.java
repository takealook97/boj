import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        long M = sc.nextLong();
        long five = five(N) - five(M) - five(N - M);
        long two = two(N) - two(M) - two(N - M);
        System.out.println(Math.min(five, two));
    }

    public static int five(long a) {
        int cnt = 0;
        while (a >= 5) {
            cnt += a / 5;
            a /= 5;
        }
        return cnt;
    }

    public static int two(long a) {
        int cnt = 0;
        while (a >= 2) {
            cnt += a / 2;
            a /= 2;
        }
        return cnt;
    }
}