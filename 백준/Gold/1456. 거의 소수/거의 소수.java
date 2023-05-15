import java.util.Scanner;

public class Main {
    static long a, b;
    static int A, B;
    static boolean[] prime;
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextLong();
        b = sc.nextLong();
        A = (int) Math.sqrt(a);
        B = (int) Math.sqrt(b);

        getPrime();
        for (int i = 2; i <= B; i++) {
            if (!prime[i]) {
                getAlmostPrime(i);
            }
        }
        System.out.println(count);
    }

    static void getPrime() {
        prime = new boolean[B + 1];
        prime[0] = prime[1] = true;
        for (int i = 2; i < Math.sqrt(B); i++) {
            for (int j = i * i; j <= B; j += i) {
                prime[j] = true;
            }
        }
    }

    static void getAlmostPrime(int prime) {
        int index = 2;
        while (true) {
            long number = (long) Math.pow(prime, index);
            if (a <= number && number <= b) count++;
            else if (number > b) break;
            index++;
        }
    }
}
