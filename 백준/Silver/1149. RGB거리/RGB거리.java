import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int[][] sum;
    static final int COLOR_COUNT = 3;
    static final int R = 0;
    static final int G = 1;
    static final int B = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][COLOR_COUNT];
        sum = new int[N][COLOR_COUNT];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[i][R] = Integer.parseInt(st.nextToken());
            board[i][G] = Integer.parseInt(st.nextToken());
            board[i][B] = Integer.parseInt(st.nextToken());
        }

        sum[0][R] = board[0][R];
        sum[0][G] = board[0][G];
        sum[0][B] = board[0][B];

        System.out.println(Math.min(getMin(N - 1, R),
                Math.min(getMin(N - 1, G), getMin(N - 1, B))));
    }

    static int getMin(int idx, int color) {
        if (sum[idx][color] == 0) {
            if (color == R) {
                sum[idx][R] = Math.min(getMin(idx - 1, G), getMin(idx - 1, B)) + board[idx][R];
            } else if (color == G) {
                sum[idx][G] = Math.min(getMin(idx - 1, R), getMin(idx - 1, B)) + board[idx][G];
            } else if (color == B) {
                sum[idx][B] = Math.min(getMin(idx - 1, R), getMin(idx - 1, G)) + board[idx][B];
            }
        }
        return sum[idx][color];
    }
}
