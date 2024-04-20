import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] -
                        dp[i - 1][j - 1] + board[i][j];
            }
        }

        System.out.println(findMax());
    }

    static int findMax() {
        int result = Integer.MIN_VALUE;

        for (int fromY = 1; fromY <= N; fromY++) {
            for (int fromX = 1; fromX <= M; fromX++) {
                for (int toY = fromY; toY <= N; toY++) {
                    for (int toX = fromX; toX <= M; toX++) {
                        int sum = dp[toY][toX] -
                                (dp[fromY - 1][toX] + dp[toY][fromX - 1] - dp[fromY - 1][fromX - 1]);
                        result = Math.max(result, sum);
                    }
                }
            }
        }

        return result;
    }
}
