import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static int N;
    static int K;
    static int[][] arr;
    static boolean[][] check;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N][M];
            check = new boolean[N][M];
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                arr[Y][X] = 1;
            }
            int count = 0;
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[j][k] == 1 && !check[j][k]) {
                        bfs(j, k);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }

    static void bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        while (!queue.isEmpty()) {
            y = queue.peek()[0];
            x = queue.peek()[1];
            check[y][x] = true;
            queue.poll();
            for (int i = 0; i < 4; i++) {
                int cy = y + dy[i];
                int cx = x + dx[i];
                if (0 <= cy && cy < N && 0 <= cx && cx < M) {
                    if (!check[cy][cx] && arr[cy][cx] == 1) {
                        queue.add(new int[]{cy, cx});
                        check[cy][cx] = true;
                    }
                }
            }
        }
    }
}