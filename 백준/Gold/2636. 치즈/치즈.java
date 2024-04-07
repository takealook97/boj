import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static Queue<Point> queue = new ArrayDeque<>();
    static Set<Point> meltSet = new HashSet<>();
    static int lastCount, answer = 0;
    static final int CHEESE = 1, EMPTY = 0;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (!isDone()) {
            setVisited();
            melt();
            answer++;
        }

        System.out.println(answer + "\n" + lastCount);
    }

    static void setVisited() {
        // refresh visited
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        // find init
        Point start = null;
        out:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == EMPTY) {
                    start = new Point(i, j);
                    break out;
                }
            }
        }

        // check visited
        lastCount = 0;
        queue.clear();
        queue.add(start);
        visited[start.y][start.x] = true;
        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if (isPossible(nextY, nextX)) {
                    Point next = new Point(nextY, nextX);
                    if (board[nextY][nextX] == EMPTY) {
                        queue.add(next);
                        visited[nextY][nextX] = true;
                    } else {
                        if (meltSet.add(next)) {
                            lastCount++;
                        }
                    }
                }
            }
        }
    }

    static boolean isPossible(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M && !visited[y][x];
    }

    static void melt() {
        for (Point point : meltSet) {
            board[point.y][point.x] = EMPTY;
        }
    }

    static boolean isDone() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == CHEESE) {
                    return false;
                }
            }
        }

        return true; // empty
    }
}
