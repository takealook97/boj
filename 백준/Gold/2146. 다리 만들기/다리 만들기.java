import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visit;
    static int result = 200;

    static class Position {
        int y, x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Point {
        int y, x, group, bridge;

        public Point(int y, int x, int group, int bridge) {
            this.y = y;
            this.x = x;
            this.group = group;
            this.bridge = bridge;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int group = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1 && !visit[i][j]) {
                    getGroup(i, j, group);
                    group++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) {
                    visit = new boolean[N][N];
                    getDistance(i, j, arr[i][j]);
                }
            }
        }

        System.out.println(result - 1);
    }

    static void getGroup(int y, int x, int group) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(y, x));
        visit[y][x] = true;
        arr[y][x] = group;
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int nowY = position.y;
            int nowX = position.x;
            for (int i = 0; i < 4; i++) {
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N) {
                    if (!visit[nextY][nextX] && arr[nextY][nextX] == 1) {
                        visit[nextY][nextX] = true;
                        arr[nextY][nextX] = group;
                        queue.add(new Position(nextY, nextX));
                    }
                }
            }
        }
    }

    static void getDistance(int y, int x, int group) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(y, x, group, 0));
        visit[y][x] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int nowY = point.y;
            int nowX = point.x;
            int nowG = point.group;
            int nowB = point.bridge;
            if (nowG != group && nowG != 0) {
                result = Math.min(result, nowB);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N) {
                    if (!visit[nextY][nextX]) {
                        visit[nextY][nextX] = true;
                        if (arr[nextY][nextX] == 0) {
                            queue.add(new Point(nextY, nextX, nowG, nowB + 1));
                        } else {
                            queue.add(new Point(nextY, nextX, arr[nextY][nextX], nowB + 1));
                        }
                    }
                }
            }
        }
    }
}
