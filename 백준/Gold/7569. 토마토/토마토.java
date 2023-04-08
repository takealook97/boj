import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int H;
    static int[][][] arr;
    static boolean[][][] visit;
    static int[] dx = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};

    static class Position {
        int x;
        int y;
        int z;
        int day;

        public Position(int z, int y, int x, int day) {
            this.z = z;
            this.y = y;
            this.x = x;
            this.day = day;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());//x
        N = Integer.parseInt(st.nextToken());//y
        H = Integer.parseInt(st.nextToken());//z
        arr = new int[H][N][M];
        visit = new boolean[H][N][M];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
        bfs();

    }

    static void bfs() {
        Queue<Position> queue = new LinkedList<>();
        int day = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[i][j][k] == 1) queue.add(new Position(i, j, k, 0));
                }
            }
        }
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            day = position.day;
            for (int i = 0; i < 6; i++) {
                int nz = position.z + dz[i];
                int ny = position.y + dy[i];
                int nx = position.x + dx[i];
                if (0 <= nz && nz < H && 0 <= ny && ny < N && 0 <= nx && nx < M) {
                    if (arr[nz][ny][nx] == 0) {
                        arr[nz][ny][nx] = 1;
                        queue.add(new Position(nz, ny, nx, day + 1));
                    }
                }
            }
        }
        if (check()) System.out.println(day);
        else System.out.println(-1);
    }

    static boolean check() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (arr[i][j][k] == 0) return false;
                }
            }
        }
        return true;
    }
}
