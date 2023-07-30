import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[] visit = new boolean[26];
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j) - 'A';
            }
        }
        dfs(0, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int y, int x, int count) {
        if (visit[board[y][x]]) {
            answer = Math.max(answer, count);
            return;
        }
        visit[board[y][x]] = true;
        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (0 <= nextY && nextY < N && 0 <= nextX && nextX < M) {
                dfs(nextY, nextX, count + 1);
            }
        }
        visit[board[y][x]] = false;
    }
}
