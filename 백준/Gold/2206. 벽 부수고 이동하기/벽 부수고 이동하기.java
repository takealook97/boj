import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][][] visit;
    static int count = 1000000;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Point {
        int y;
        int x;
        int count;
        int check;

        public Point(int y, int x, int count, int check) {
            this.y = y;
            this.x = x;
            this.count = count;
            this.check = check;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        visit = new boolean[N][M][2];
        if(N == 1 && M == 1){
            System.out.println(1);
            System.exit(0);
        }

        N--;
        M--;
        bfs();
        if (count != 1000000) System.out.println(count + 1);
        else System.out.println(-1);
    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 0, 0));
        visit[0][0][0] = true;
        visit[0][0][1] = true;
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int nowY = now.y;
            int nowX = now.x;
            int nowCount = now.count;
            int nowCheck = now.check;

            if (nowY == N && nowX == M) {
                count = nowCount;
                break;
            } else {
                for (int i = 0; i < 4; i++) {
                    int nextY = nowY + dy[i];
                    int nextX = nowX + dx[i];
                    if (0 <= nextY && nextY <= N && 0 <= nextX && nextX <= M) {
                        if (arr[nextY][nextX] == 0 && !visit[nextY][nextX][now.check]) {
                            visit[nextY][nextX][now.check] = true;
                            queue.add(new Point(nextY, nextX, nowCount + 1, now.check));
                        } else if (arr[nextY][nextX] == 1) {
                            if(now.check == 0 && !visit[nextY][nextX][1]){
                                visit[nextY][nextX][1] = true;
                                queue.add(new Point(nextY, nextX, nowCount + 1, 1));
                            }
                        }
                    }
                }
            }
        }
    }
}
