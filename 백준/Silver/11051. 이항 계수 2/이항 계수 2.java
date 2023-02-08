import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[][] arr = new int[1001][1001];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j || j == 0)
                    arr[i][j] = 1;
                else
                    arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j]) % 10007;
            }
        }
        System.out.print(arr[N][K]);
    }
}