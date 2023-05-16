import java.util.Scanner;

public class Main {
    static int N;
    static boolean[] prime;
    static int INF = 2000001;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        prime = new boolean[INF + 1];
        prime[0] = prime[1] = true;
        getPrime();
        for (int i = N; i <= prime.length; i++) {
            if (!prime[i] && isPel(i)) {
                System.out.println(i);
                break;
            }
        }
    }

    static void getPrime() {
        for (int i = 2; i < Math.sqrt(INF); i++) {
            for (int j = i * i; j <= INF; j += i) {
                prime[j] = true;
            }
        }
    }

    static boolean isPel(int x) {
        String number = String.valueOf(x);
        int length = number.length();
        if (length == 1) return true;
        int start = 0;
        int end = length - 1;
        while (start <= end) {
            if (number.charAt(start) != number.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}