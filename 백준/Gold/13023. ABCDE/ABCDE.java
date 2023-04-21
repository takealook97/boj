import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer>[] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        for (int i = 0; i < N; i++) {
            dfs(i, 1);
        }
        System.out.println(0);
    }

    static void dfs(int num, int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0);
        }
        visit[num] = true;
        for (int i : arr[num]) {
            if (!visit[i]) {
                dfs(i, depth + 1);
            }
        }
        visit[num] = false;
    }
}
