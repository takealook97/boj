import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int Z, Y, X;
    static char[][][] board;
    static boolean[][][] visited;
    static Queue<Point> queue = new ArrayDeque<>();
    static int[] dy = {-1, 1, 0, 0, 0, 0}, dx = {0, 0, -1, 1, 0, 0}, dz = {0, 0, 0, 0, 1, -1};
    static int answer;

    static final int MAX_SIZE = 30;
    static final char START = 'S', END = 'E', EMPTY = '.', WALL = '#';
    static final String POSSIBLE_START = "Escaped in ", POSSIBLE_END = " minute(s).", IMPOSSIBLE = "Trapped!";

    static class Point {
        int z, y, x, count;

        public Point(int z, int y, int x, int count) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.count = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return z == point.z && y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(z, y, x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        board = new char[MAX_SIZE][MAX_SIZE][MAX_SIZE];
        visited = new boolean[MAX_SIZE][MAX_SIZE][MAX_SIZE];
        Point start = null, end = null;

        while (true) {
            st = new StringTokenizer(br.readLine());
            Z = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            if (Z == 0 && Y == 0 && X == 0) {
                break;
            }

            refresh();

            String line;

            for (int z = 0; z < Z; z++) {
                for (int y = 0; y < Y; y++) {
                    line = br.readLine();
                    for (int x = 0; x < X; x++) {
                        board[z][y][x] = line.charAt(x);
                        if (board[z][y][x] == START) {
                            start = new Point(z, y, x, 0);
                        } else if (board[z][y][x] == END) {
                            end = new Point(z, y, x, 0);
                        } else if (board[z][y][x] == WALL) {
                            visited[z][y][x] = true;
                        }
                    }
                }
                br.readLine();
            }

            search(start, end);

            if (answer > 0) {
                sb.append(POSSIBLE_START).append(answer).append(POSSIBLE_END).append("\n");
            } else {
                sb.append(IMPOSSIBLE).append("\n");
            }
        }

        System.out.print(sb);
    }

    static void refresh() {
        answer = 0;
        queue.clear();

        for (int z = 0; z < Z; z++) {
            for (int y = 0; y < Y; y++) {
                for (int x = 0; x < X; x++) {
                    visited[z][y][x] = false;
                }
            }
        }
    }

    static void search(Point start, Point end) {
        queue.add(start);
        visited[start.z][start.y][start.x] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.equals(end)) {
                answer = now.count;
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nextZ = now.z + dz[i];
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (isPossible(nextZ, nextY, nextX)) {
                    visited[nextZ][nextY][nextX] = true;
                    queue.add(new Point(nextZ, nextY, nextX, now.count + 1));
                }
            }
        }
    }

    static boolean isPossible(int z, int y, int x) {
        return 0 <= z && z < Z && 0 <= y && y < Y && 0 <= x && x < X &&
                !visited[z][y][x];
    }
}
