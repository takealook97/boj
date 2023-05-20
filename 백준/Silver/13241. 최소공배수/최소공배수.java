import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextInt();
        long b = sc.nextInt();
        System.out.println(a * b / getGCD(a, b));
    }

    static long getGCD(long a, long b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}
