import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static ArrayList<Point> virusList = new ArrayList<>();
    static ArrayList<Point> blankList = new ArrayList<>();
    static int answer = 0;

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
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    virusList.add(new Point(i, j));
                } else if (board[i][j] == 0) {
                    blankList.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < blankList.size() - 2; i++) {
            for (int j = i + 1; j < blankList.size() - 1; j++) {
                for (int k = j + 1; k < blankList.size(); k++) {
                    Point[] walls = {blankList.get(i), blankList.get(j), blankList.get(k)};
                    bfs(walls);
                }
            }	
        }
        System.out.println(answer);
    }


    static void bfs(Point[] walls) {
        int[][] temp = new int[N][M];
        for (int i = 0; i < N; i++) {
            if (M >= 0) System.arraycopy(board[i], 0, temp[i], 0, M);// 깊은 복사로 가져온다.
        }
        for (Point point : walls) {
            temp[point.y][point.x] = 1;
        }
        visit = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        for (Point point : virusList) {
            visit[point.y][point.x] = true;
            queue.add(new Point(point.y, point.x));
        }
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M && !visit[nextY][nextX] && temp[nextY][nextX] == 0) {
                    visit[nextY][nextX] = true;
                    temp[nextY][nextX] = 2;
                    queue.add(new Point(nextY, nextX));
                }
            }
        }

        int safeZoneCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] == 0) {
                    safeZoneCount++;
                }
            }
        }
        answer = Math.max(answer, safeZoneCount);
    }
}
