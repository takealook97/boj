import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static boolean[] prime;
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        prime = new boolean[b + 1];
        for (int i = 1; i <= b; i++) {
            prime[i] = isPrime(i);
            if (prime[i]) list.add(i);
        }
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (isUnderPrime(i)) count++;
        }
        System.out.println(count);
    }

    static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static boolean isUnderPrime(int num) {
        int count = 0;
        for (int i : list) {
            while (true) {
                if (num % i == 0) {
                    num /= i;
                    count++;
                } else break;
            }
            if(num == 1) break;
        }
        return list.contains(count);
    }
}
