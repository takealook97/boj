import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String num = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = num.charAt(j) - '0';
            }
        }
        int length = Math.min(N, M);
        out:
        while (true) {
            for (int i = 0; i < N - length; i++) {
                for (int j = 0; j < M - length; j++) {
                    int a = arr[i][j];
                    int b = arr[i][j + length];
                    int c = arr[i + length][j];
                    int d = arr[i + length][j + length];
                    if (a == b && b == c && c == d) break out;
                }
            }
            length--;
        }
        System.out.println((length + 1) * (length + 1));
    }
}
