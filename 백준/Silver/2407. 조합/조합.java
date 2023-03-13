import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        System.out.println(nCr(n, m));
    }

    static BigInteger nCr(int n, int m) {
        BigInteger a = BigInteger.valueOf(1);
        BigInteger b = BigInteger.valueOf(1);
        for (int i = 0; i < m; i++) {
            a = a.multiply(BigInteger.valueOf(n - i));
            b = b.multiply(BigInteger.valueOf(i + 1));
        }
        return a.divide(b);

    }
}