import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int white;
    static int blue;

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
        checkParts(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void checkParts(int h, int w, int size) {
        if (checkColors(h, w, size)) {
            if (arr[h][w] == 0) {
                white++;
            } else {
                blue++;
            }
            return;
        }
        size /= 2;
        checkParts(h, w, size);
        checkParts(h, w + size, size);
        checkParts(h + size, w, size);
        checkParts(h + size, w + size, size);
    }

    static boolean checkColors(int h, int w, int size) {
        int color = arr[h][w];
        for (int i = h; i < h + size; i++) {
            for (int j = w; j < w + size; j++) {
                if (arr[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}