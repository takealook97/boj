import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int[][] board;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        board = new int[H + 1][W];
        Arrays.fill(board[H], 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int amount = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= amount; j++) {
                board[H - j][i] = 1;
            }
        }

        for (int i = 0; i < H; i++) {
            int start = 0, end = W - 1;

            for (int j = 0; j < W; j++) {
                if (board[i][j] == 1) {
                    start = j;
                    break;
                }
            }

            for (int j = W - 1; j >= 0; j--) {
                if (board[i][j] == 1) {
                    end = j;
                    break;
                }
            }

            if ((start == 0 && board[i][start] != 1) ||
                    end == W - 1 && board[i][end] != 1) {
                continue;
            }

            for (int j = start; j <= end; j++) {
                if (board[i][j] == 0) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
