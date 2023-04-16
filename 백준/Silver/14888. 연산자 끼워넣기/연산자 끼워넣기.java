import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static int[] operator = new int[4];
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
        dfs(arr[0], 1);
        System.out.println(max + "\n" + min);
    }

    static void dfs(int num, int depth) {
        if (depth == N) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;
                if (i == 0) {
                    dfs(num + arr[depth], depth + 1);
                } else if (i == 1) {
                    dfs(num - arr[depth], depth + 1);
                } else if (i == 2) {
                    dfs(num * arr[depth], depth + 1);
                } else dfs(num / arr[depth], depth + 1);
                operator[i]++;
            }
        }
    }
}
