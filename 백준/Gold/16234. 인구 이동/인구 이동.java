import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int time = 0;

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
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (checkAll()) {
            ArrayList<Point> changeList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (checkAdjacent(i, j)) {
                        changeList.add(new Point(i, j));
                    }
                }
            }
            visit = new boolean[N][N];
            for (Point point : changeList) {
                if (!visit[point.y][point.x]) setAdjacent(point.y, point.x);
            }
            time++;
        }
        System.out.println(time);

    }

    static boolean checkAll() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (checkAdjacent(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkAdjacent(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N) {
                int gap = Math.abs(arr[y][x] - arr[nextY][nextX]);
                if (L <= gap && gap <= R) {
                    return true;
                }
            }
        }
        return false;
    }

    static void setAdjacent(int y, int x) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(y, x));
        visit[y][x] = true;
        ArrayList<Point> list = new ArrayList<>();
        list.add(new Point(y, x));
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int nowY = now.y;
            int nowX = now.x;
            for (int i = 0; i < 4; i++) {
                int nextY = nowY + dy[i];
                int nextX = nowX + dx[i];
                if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N && !visit[nextY][nextX]) {
                    int gap = Math.abs(arr[nowY][nowX] - arr[nextY][nextX]);
                    if (L <= gap && gap <= R) {
                        Point next = new Point(nextY, nextX);
                        visit[nextY][nextX] = true;
                        list.add(next);
                        queue.add(next);
                    }
                }
            }
        }
        if (list.size() > 1) {
            int average = getAverage(list);
            setAverage(list, average);
        } else {
            visit[y][x] = false;
        }
    }

    static int getAverage(ArrayList<Point> list) {
        int sum = 0;
        for (Point point : list) {
            sum += arr[point.y][point.x];
        }
        return sum / list.size();
    }

    static void setAverage(ArrayList<Point> list, int average) {
        for (Point point : list) {
            arr[point.y][point.x] = average;
        }
    }
}
