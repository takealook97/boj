import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static boolean[] isPrime;
    static ArrayList<Long> sumOfPrime = new ArrayList<>();
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        if(N == 2) {
            System.out.println(1);
            System.exit(0);
        }
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        makePrimeArray();

        long sum = 0;
        boolean firstCheck = false;
        int left = 0;
        int right = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                sum += i;
                if (sum >= N && !firstCheck) {
                    right = sumOfPrime.size();
                    firstCheck = true;
                }
                sumOfPrime.add(sum);
            }
        }

        while (left < right) {
            if (sumOfPrime.get(right) == N) {
                count++;
                if (right + 1 < sumOfPrime.size()) {
                    right++;
                } else left++;
                continue;
            }
            long sumOfRange = sumOfPrime.get(right) - sumOfPrime.get(left);
            if (sumOfRange < N) {
                if (right + 1 < sumOfPrime.size()) {
                    right++;
                } else left++;
            } else if (sumOfRange > N) {
                left++;
            } else {
                count++;
                if (right + 1 < sumOfPrime.size()) {
                    right++;
                } else left++;
            }
        }
        System.out.println(count);
    }

    static void makePrimeArray() {
        for (int i = 2; i <= Math.sqrt(N); i++) {
            for (int j = i * i; j <= N; j += i) {
                isPrime[j] = false;
            }
        }
    }
}