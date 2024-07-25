import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
    static int answer = 0;

    static class Point {
        int y, x, dist;

        public Point(int y, int x, int dist) {
            this.y = y;
            this.x = x;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'L') {
                    answer = Math.max(answer, getDist(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    static int getDist(int startY, int startX) {
        visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startY, startX, 0));
        visited[startY][startX] = true;

        int maxVal = 0;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            maxVal = Math.max(maxVal, now.dist);

            for (int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];

                if (isInRange(nextY, nextX) && !visited[nextY][nextX] && board[nextY][nextX] == 'L') {
                    visited[nextY][nextX] = true;
                    queue.add(new Point(nextY, nextX, now.dist + 1));
                }
            }
        }

        return maxVal;
    }

    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
