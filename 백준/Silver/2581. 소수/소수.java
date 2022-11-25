import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        boolean[] check = new boolean[N - M + 1];
        int min = 0;
        int sum = 0;

        for (int i = 0; i <= N - M; i++) {
            for (int j = 2; j < i + M; j++) {
                if ((i + M) % j == 0) {
                    check[i] = false;
                    break;
                } else {
                    check[i] = true;
                }
            }
            if (check[i] == true) {
                sum += (i + M);
            }
        }
        for (int i = 0; i <= N - M; i++) {
            for (int j = 2; j < i + M; j++) {
                if ((i + M) % j == 0) {
                    check[i] = false;
                    break;
                } else {
                    check[i] = true;
                }
            }
            if (check[i] == true) {
                min = (i + M);
                break;
            }
        }

        if (M == 1 || M == 2 && N > 1) {
            min = 2;
            sum = sum + 2;
        }
        if (N == 1 || min == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
        }
    }
}