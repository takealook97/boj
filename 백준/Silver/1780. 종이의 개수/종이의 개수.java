import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int gray;
    static int white;
    static int black;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        devide(0, 0, N);
        System.out.println(gray);
        System.out.println(white);
        System.out.println(black);
    }

    static void devide(int r, int c, int size) {
        if (check(r, c, size)) {
            if (arr[r][c] == -1) {
                gray++;
            } else if (arr[r][c] == 0) {
                white++;
            } else black++;
            return;
        }
        size /= 3;

        devide(r, c, size);
        devide(r, c + size, size);
        devide(r, c + 2 * size, size);

        devide(r + size, c, size);
        devide(r + size, c + size, size);
        devide(r + size, c + 2 * size, size);

        devide(r + 2 * size, c, size);
        devide(r + 2 * size, c + size, size);
        devide(r + 2 * size, c + 2 * size, size);
    }

    static boolean check(int r, int c, int size) {
        int color = arr[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (color != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}