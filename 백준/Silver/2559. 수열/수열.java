import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] result = new int[N - K + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < result.length; i++) {
            for (int j = i; j < i + K; j++) {
                result[i] += arr[j];
            }
            max = Math.max(max, result[i]);
        }
        System.out.println(max);
    }
}