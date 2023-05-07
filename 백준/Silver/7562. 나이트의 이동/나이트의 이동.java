import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int I;
    static int[][] visit;
    static int a, b, c, d;
    static int result;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < TC; tc++) {
            I = Integer.parseInt(br.readLine());
            visit = new int[I][I];
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            visit[a][b] = 0;
            result = 0;
            bfs(a, b);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            if (nowX == c && nowY == d) {
                result = visit[nowX][nowY];
                return;
            } else {
                for (int i = 0; i < 8; i++) {
                    int nextX = nowX + dx[i];
                    int nextY = nowY + dy[i];
                    if (0 <= nextX && nextX < I && 0 <= nextY && nextY < I && visit[nextX][nextY] == 0) {
                        visit[nextX][nextY] = visit[nowX][nowY] + 1;
                        queue.add(new int[]{nextX, nextY});
                    }
                }
            }
        }
    }
}
