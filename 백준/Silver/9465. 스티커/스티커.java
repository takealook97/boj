import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, n;
    static int[][] arr;
    static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            arr = new int[2][n];
            result = new int[2][n];
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if (n == 1) {
                sb.append(Math.max(arr[0][0], arr[1][0])).append("\n");
                continue;
            }
            
            result[0][0] = arr[0][0];
            result[1][0] = arr[1][0];
            result[0][1] = result[1][0] + arr[0][1];
            result[1][1] = result[0][0] + arr[1][1];
            for (int i = 2; i < n; i++) {
                int firstBefore = Math.max(result[0][i - 2] + arr[0][i], result[1][i - 2] + arr[0][i]);
                int secondBefore = Math.max(result[0][i - 2] + arr[1][i], result[1][i - 2] + arr[1][i]);
                result[0][i] = Math.max(result[1][i - 1] + arr[0][i], firstBefore);
                result[1][i] = Math.max(result[0][i - 1] + arr[1][i], secondBefore);
            }
            sb.append(Math.max(result[0][n - 1], result[1][n - 1])).append("\n");
        }
        System.out.print(sb);
    }
}
