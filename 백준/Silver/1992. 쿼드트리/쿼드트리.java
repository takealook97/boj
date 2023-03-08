import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }
        bfs(0, 0, N);
        System.out.println(sb);
    }

    static void bfs(int x, int y, int size) {
        if (check(x, y, size)) {
            sb.append(arr[x][y]);
        } else {
            size /= 2;
            sb.append("(");
            bfs(x, y, size);
            bfs(x, y + size, size);
            bfs(x + size, y, size);
            bfs(x + size, y + size, size);
            sb.append(")");
        }
    }

    static boolean check(int x, int y, int size) {
        int standard = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (standard != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}