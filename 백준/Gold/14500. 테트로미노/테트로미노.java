import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());//y
        M = Integer.parseInt(st.nextToken());//x
        arr = new int[N][M];
        visit = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                bfs(i, j);
            }
        }
        System.out.println(result);
    }

    static void dfs(int y, int x, int depth, int sum) {
        if (depth == 4) {
            if (result < sum) {
                result = sum;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M) {
                if (!visit[nextY][nextX]) {
                    visit[nextY][nextX] = true;
                    int nextSum = sum + arr[nextY][nextX];
                    dfs(nextY, nextX, depth + 1, nextSum);
                    visit[nextY][nextX] = false;
                }
            }
        }
    }

    static void bfs(int y, int x) {
        int sum = arr[y][x];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (0 <= y + dy[i] && y + dy[i] < N && 0 <= x + dx[i] && x + dx[i] < M) {
                sum += arr[y + dy[i]][x + dx[i]];
                count++;
            }
        }
        if (count == 3) {
            if (result < sum) {
                result = sum;
            }
        } else if (count == 4) {
            for (int i = 0; i < 4; i++) {
                int temp = sum - arr[y + dy[i]][x + dx[i]];
                if (result < temp) {
                    result = temp;
                }
            }
        }
    }
}
