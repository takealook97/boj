import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        String[] line = new String[N];
        char[][] chess = new char[N][M];
        char[][] standard = new char[8][8];
        for (int i = 0; i < N; i++) {
            line[i] = sc.next();
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                chess[i][j] = line[i].charAt(j);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    standard[i][j] = 'W';
                } else standard[i][j] = 'B';
            }
        }
        int[] count = new int[(N - 7) * (M - 7)];
        int x = 0;
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if (chess[k + i][l + j] != standard[k][l]) {
                            count[x]++;
                        }
                    }
                }
                x++;
            }
        }
        for (int i = 0; i < (N - 7) * (M - 7); i++) {
            if (count[i] > 32) {
                count[i] = 64 - count[i];
            }
        }
        Arrays.sort(count);
        System.out.println(count[0]);
    }
}