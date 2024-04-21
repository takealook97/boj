import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H, W, sr, sc, fr, fc;
    static int[][] board;
    static boolean[][][] visited;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int answer = -1;
    static final int EMPTY = 0, WALL = 1;

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    static class Square {
        Point p0, p1, p2, p3;
        int count;

        public Square(Point p0, Point p1, Point p2, Point p3, int count) {
            this.p0 = p0;
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[4][N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        fr = Integer.parseInt(st.nextToken()) - 1;
        fc = Integer.parseInt(st.nextToken()) - 1;

        findPath();

        System.out.println(answer);
    }

    static void findPath() {
        Point destination = new Point(fr, fc);
        Queue<Square> queue = new ArrayDeque<>();
        Square square = new Square(
                new Point(sr, sc),
                new Point(sr, sc + W - 1),
                new Point(sr + H - 1, sc),
                new Point(sr + H - 1, sc + W - 1),
                0
        );
        queue.add(square);
        checkVisited(square);

        while (!queue.isEmpty()) {
            Square now = queue.poll();
            if (now.p0.equals(destination)) {
                answer = now.count;
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (!isPossible(now.p0.y + dy[i], now.p0.x + dx[i],
                        now.p1.y + dy[i], now.p1.x + dx[i],
                        now.p2.y + dy[i], now.p2.x + dx[i],
                        now.p3.y + dy[i], now.p3.x + dx[i])) {
                    continue;
                }

                Point nextP0 = new Point(now.p0.y + dy[i], now.p0.x + dx[i]);
                Point nextP1 = new Point(now.p1.y + dy[i], now.p1.x + dx[i]);
                Point nextP2 = new Point(now.p2.y + dy[i], now.p2.x + dx[i]);
                Point nextP3 = new Point(now.p3.y + dy[i], now.p3.x + dx[i]);
                Square nextSquare = new Square(nextP0, nextP1, nextP2, nextP3, now.count + 1);

                checkVisited(nextSquare);

                queue.add(nextSquare);
            }
        }
    }

    static void checkVisited(Square s) {
        visited[0][s.p0.y][s.p0.x] = true;
        visited[0][s.p0.y][s.p0.x] = true;
        visited[1][s.p1.y][s.p1.x] = true;
        visited[1][s.p1.y][s.p1.x] = true;
        visited[2][s.p2.y][s.p2.x] = true;
        visited[2][s.p2.y][s.p2.x] = true;
        visited[3][s.p3.y][s.p3.x] = true;
        visited[3][s.p3.y][s.p3.x] = true;
    }

    static boolean isPossible(int p0y, int p0x, int p1y, int p1x,
                              int p2y, int p2x, int p3y, int p3x) {
        // check range
        if (0 <= p0y && p0y < N &&
                0 <= p0x && p0x < M &&
                0 <= p1y && p1y < N &&
                0 <= p1x && p1x < M &&
                0 <= p2y && p2y < N &&
                0 <= p2x && p2x < M &&
                0 <= p3y && p3y < N &&
                0 <= p3x && p3x < M &&
                // check visited
                !visited[0][p0y][p0x] &&
                !visited[1][p1y][p1x] &&
                !visited[2][p2y][p2x] &&
                !visited[3][p3y][p3x]) {
            // check wall
            for (int x = p0x; x <= p1x; x++) {
                if (board[p0y][x] == WALL || board[p2y][x] == WALL) {
                    return false;
                }
            }

            for (int y = p0y; y <= p2y; y++) {
                if (board[y][p0x] == WALL || board[y][p1x] == WALL) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
