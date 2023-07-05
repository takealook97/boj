import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int result = 0;

    static ArrayList<Point> virus = new ArrayList<>();
    static ArrayList<Point> blank = new ArrayList<>();

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    virus.add(new Point(i, j));
                } else if (arr[i][j] == 0) {
                    blank.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < blank.size() - 2; i++) {
            for (int j = i + 1; j < blank.size() - 1; j++) {
                for (int k = j + 1; k < blank.size(); k++) {
                    Point[] walls = {blank.get(i), blank.get(j), blank.get(k)};
                    bfs(walls);
                }
            }
        }
        System.out.println(result);
    }


    static void bfs(Point[] walls) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(arr[i], 0, temp[i], 0, M);
        }
        for (Point point : walls) {
            temp[point.y][point.x] = 1;
        }
        boolean[][] visit = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        for (Point point : virus) {
            visit[point.y][point.x] = true;
            queue.add(new Point(point.y, point.x));
        }
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int nowY = now.y;
            int nowX = now.x;
            for (int i = 0; i < 4; i++) {
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M && !visit[nextY][nextX] && temp[nextY][nextX] == 0) {
                    visit[nextY][nextX] = true;
                    temp[nextY][nextX] = 2;
                    queue.add(new Point(nextY, nextX));
                }
            }
        }
        result = Math.max(result, getSafeZone(temp));
    }


    static int getSafeZone(int[][] temp) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
