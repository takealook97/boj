import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        check[0][0] = true;
        bfs();
        System.out.println(arr[N - 1][M - 1]);
    }

    static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {//이동한 좌표가 범위 이내이고
                    if (!check[nextX][nextY] && arr[nextX][nextY] == 1) {//아직 방문하지 않은 좌표일 때
                        queue.add(new int[]{nextX, nextY});//queue에 그 좌표를 추가
                        arr[nextX][nextY] = arr[nowX][nowY] + 1;//누적으로 count 대체하기
                        check[nextX][nextY] = true;
                    }
                }
            }
        }
    }
}