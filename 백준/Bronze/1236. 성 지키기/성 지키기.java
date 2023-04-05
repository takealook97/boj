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
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '.') arr[i][j] = 0;
                else arr[i][j] = 1;
            }
        }
        int R = 0;
        int C = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1) {
                    R++;
                    break;
                }
            }
        }
        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (arr[i][j] == 1) {
                    C++;
                    break;
                }
            }
        }

        System.out.println(Math.max(N - R, M - C));
    }
}