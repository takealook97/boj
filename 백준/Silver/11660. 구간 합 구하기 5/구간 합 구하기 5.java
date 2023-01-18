import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        int[][] arrD = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arrD[i][j] = arrD[i][j - 1] + arrD[i - 1][j] - arrD[i - 1][j - 1] + arr[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            int result = arrD[x2][y2] - arrD[x1 - 1][y2] - arrD[x2][y1 - 1] + arrD[x1 - 1][y1 - 1];
            System.out.println(result);
        }
    }
}