import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static char[][] arr;
    static boolean[][] check;
    static boolean[][] checkRG;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int R;
    static int G;
    static int B;
    static int RG;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        check = new boolean[N][N];
        checkRG = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = temp.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 'R' && !check[i][j]) {
                    bfsR(i, j);
                    R++;
                } else if (arr[i][j] == 'G' && !check[i][j]) {
                    bfsG(i, j);
                    G++;
                } else if (arr[i][j] == 'B' && !check[i][j]) {
                    bfsB(i, j);
                    B++;
                }
                if (arr[i][j] == 'R' || arr[i][j] == 'G') {
                    if (!checkRG[i][j]) {
                        bfsRG(i, j);
                        RG++;
                    }
                }
            }
        }
        System.out.println(R + G + B + " " + (RG + B));
    }

    static void bfsR(int y, int x) {
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
                if (0 <= cy && cy < N && 0 <= cx && cx < N) {
                    if (!check[cy][cx] && arr[cy][cx] == 'R') {
                        queue.add(new int[]{cy, cx});
                        check[cy][cx] = true;
                    }
                }
            }
        }
    }

    static void bfsG(int y, int x) {
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
                if (0 <= cy && cy < N && 0 <= cx && cx < N) {
                    if (!check[cy][cx] && arr[cy][cx] == 'G') {
                        queue.add(new int[]{cy, cx});
                        check[cy][cx] = true;
                    }
                }
            }
        }
    }

    static void bfsB(int y, int x) {
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
                if (0 <= cy && cy < N && 0 <= cx && cx < N) {
                    if (!check[cy][cx] && arr[cy][cx] == 'B') {
                        queue.add(new int[]{cy, cx});
                        check[cy][cx] = true;
                    }
                }
            }
        }
    }

    static void bfsRG(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        while (!queue.isEmpty()) {
            y = queue.peek()[0];
            x = queue.peek()[1];
            checkRG[y][x] = true;
            queue.poll();
            for (int i = 0; i < 4; i++) {
                int cy = y + dy[i];
                int cx = x + dx[i];
                if (0 <= cy && cy < N && 0 <= cx && cx < N) {
                    if (!checkRG[cy][cx]) {
                        if (arr[cy][cx] == 'R' || arr[cy][cx] == 'G') {
                            queue.add(new int[]{cy, cx});
                            checkRG[cy][cx] = true;
                        }
                    }
                }
            }
        }
    }
}