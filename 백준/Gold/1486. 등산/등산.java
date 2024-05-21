import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, T, D;
    static int[][] board;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int answer = 0;
    
    static final int INF = Integer.MAX_VALUE / 2;

    static class Point implements Comparable<Point> {
        int y, x, sum;

        public Point(int y, int x, int sum) {
            this.y = y;
            this.x = x;
            this.sum = sum;
        }

        @Override
        public int compareTo(Point o) {
            return this.sum - o.sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = charToHeight(line.charAt(j));
            }
        }

        int[][] distFromStart = getDist(0, 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (distFromStart[i][j] != INF) {
                    int[][] distFromEnd = getDist(i, j);
                    if (distFromEnd[0][0] != INF && distFromStart[i][j] + distFromEnd[0][0] <= D) {
                        answer = Math.max(answer, board[i][j]);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static int charToHeight(char c) {
        if ('A' <= c && c <= 'Z') {
            return c - 'A';
        } else {
            return c - 'a' + 26;
        }
    }

    static int[][] getDist(int startY, int startX) {
        int[][] dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(startY, startX, 0));
        dist[startY][startX] = 0;

        while (!pq.isEmpty()) {
            Point now = pq.poll();

            if (now.sum > dist[now.y][now.x]) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if (isInRange(nextY, nextX)) {
                    int gap = board[nextY][nextX] - board[now.y][now.x];
                    if (Math.abs(gap) <= T) {
                        int nextSum = now.sum + (gap <= 0 ? 1 : gap * gap);
                        if (nextSum < dist[nextY][nextX]) {
                            dist[nextY][nextX] = nextSum;
                            pq.add(new Point(nextY, nextX, nextSum));
                        }
                    }
                }
            }
        }

        return dist;
    }

    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
