import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N;
    static ArrayList<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        for (int i = 1; i <= 9; i++) {
            if (isPrime(i)) {
                dfs(i, 1);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int num, int depth) {
        if (depth == N) {
            result.add(num);
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (isPrime(num * 10 + i)) {
                dfs(num * 10 + i, depth + 1);
            }
        }
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
}