import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, y, x, K;
    static int[][] board;
    static int[] order;
    static int[] dy = {0, 0, -1, 1}, dx = {1, -1, 0, 0}; //동 서 북 남
    static int[] dice = new int[7];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        order = new int[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        rollDice();
        System.out.print(sb);
    }

    static void rollDice() {
        Arrays.fill(dice, 0);
        for (int i = 0; i < K; i++) {
            int nowOrder = order[i];
            int nextY = y + dy[nowOrder];
            int nextX = x + dx[nowOrder];
            if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M) {
                int[] temp = dice.clone();
                switch (nowOrder) {
                    case 0:
                        dice[1] = temp[4];
                        dice[3] = temp[1];
                        dice[4] = temp[6];
                        dice[6] = temp[3];
                        break;
                    case 1:
                        dice[1] = temp[3];
                        dice[3] = temp[6];
                        dice[4] = temp[1];
                        dice[6] = temp[4];
                        break;
                    case 2:
                        dice[1] = temp[5];
                        dice[2] = temp[1];
                        dice[5] = temp[6];
                        dice[6] = temp[2];
                        break;
                    case 3:
                        dice[1] = temp[2];
                        dice[2] = temp[6];
                        dice[5] = temp[1];
                        dice[6] = temp[5];
                        break;
                }

                if (board[nextY][nextX] == 0) {
                    board[nextY][nextX] = dice[6];
                } else {
                    dice[6] = board[nextY][nextX];
                    board[nextY][nextX] = 0;
                }

                sb.append(dice[1]).append("\n");
                y = nextY;
                x = nextX;
            }
        }
    }
}
