import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, W, H;
    static int[][] board;
    static boolean[][][] visit;
    static int[] jumpDy = {-1, 1, -2, 2, -2, 2, -1, 1}, jumpDx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] moveDy = {-1, 1, 0, 0}, moveDx = {0, 0, -1, 1};
    static final int SPACE = 0;
    static int answer = Integer.MAX_VALUE;

    static class Point {
        int row, col, jumpLeft, count;

        public Point(int row, int col, int jumpLeft, int count) {
            this.row = row;
            this.col = col;
            this.jumpLeft = jumpLeft;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][W];
        visit = new boolean[H][W][K + 1];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getMin();

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void getMin() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, K, 0));
        visit[0][0][K] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.row == H - 1 && now.col == W - 1) {
                answer = now.count;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nextRow = now.row + moveDx[i];
                int nextCol = now.col + moveDy[i];
                if (isMovable(nextRow, nextCol, now.jumpLeft)) {
                    visit[nextRow][nextCol][now.jumpLeft] = true;
                    queue.offer(new Point(nextRow, nextCol, now.jumpLeft, now.count + 1));
                }
            }

            if (now.jumpLeft > 0) {
                for (int i = 0; i < 8; i++) {
                    int nextRow = now.row + jumpDx[i];
                    int nextCol = now.col + jumpDy[i];
                    if (isMovable(nextRow, nextCol, now.jumpLeft - 1)) {
                        visit[nextRow][nextCol][now.jumpLeft - 1] = true;
                        queue.offer(new Point(nextRow, nextCol, now.jumpLeft - 1, now.count + 1));
                    }
                }
            }
        }
    }

    static boolean isMovable(int row, int col, int jumpCount) {
        return 0 <= row && row < H && 0 <= col && col < W && !visit[row][col][jumpCount] && board[row][col] == SPACE;
    }
}
