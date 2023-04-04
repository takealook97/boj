import java.util.*;

public class Main {
    static int C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        C = sc.nextInt();
        System.out.println(pow(A, B));
    }

    static long pow(long A, long x) {
        if (x == 1) {
            return A % C;
        }

        long temp = pow(A, x / 2);
        if (x % 2 == 1) {
            return (temp * temp % C) * A % C;
        }
        return temp * temp % C;
    }
}