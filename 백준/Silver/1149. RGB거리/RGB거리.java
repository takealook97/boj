import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        sum = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        sum[0][0] = arr[0][0];
        sum[0][1] = arr[0][1];
        sum[0][2] = arr[0][2];

        System.out.println(Math.min(getMin(N - 1, 0),
                Math.min(getMin(N - 1, 1), getMin(N - 1, 2))));
    }

    static int getMin(int index, int color) {
        if (sum[index][color] == 0) {
            if (color == 0) {
                sum[index][0] = Math.min(getMin(index - 1, 1), getMin(index - 1, 2)) + arr[index][0];
            } else if (color == 1) {
                sum[index][1] = Math.min(getMin(index - 1, 0), getMin(index - 1, 2)) + arr[index][1];
            } else {
                sum[index][2] = Math.min(getMin(index - 1, 0), getMin(index - 1, 1)) + arr[index][2];
            }
        }
        return sum[index][color];
    }
}
