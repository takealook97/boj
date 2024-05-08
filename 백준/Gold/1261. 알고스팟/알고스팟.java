import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board, distance;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

    static final int INF = 987654321;

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        distance = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distance[i], INF);
        }

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        getMin();

        System.out.println(distance[N - 1][M - 1]);
    }

    static void getMin() {
        Deque<Point> deque = new ArrayDeque<>();
        deque.add(new Point(0, 0));
        distance[0][0] = 0;

        while (!deque.isEmpty()) {
            Point now = deque.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if (isInRange(nextY, nextX)) {
                    if (distance[nextY][nextX] > distance[now.y][now.x] + board[nextY][nextX]) {
                        distance[nextY][nextX] = distance[now.y][now.x] + board[nextY][nextX];
                        Point next = new Point(nextY, nextX);
                        if (board[nextY][nextX] == 1) {
                            deque.addLast(next);
                        } else {
                            deque.addFirst(next);
                        }
                    }
                }
            }
        }
    }

    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
