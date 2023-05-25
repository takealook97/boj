import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static ArrayList<Integer>[] arr;
    static boolean[] visit;
    static int[] colors;
    static boolean result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            arr = new ArrayList[V + 1];
            visit = new boolean[V + 1];
            colors = new int[V + 1];
            result = true;
            for (int i = 1; i <= V; i++) {
                arr[i] = new ArrayList<>();
            }
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                arr[u].add(v);
                arr[v].add(u);
            }
            for (int i = 1; i <= V; i++) {
                if (colors[i] == 0) {
                    dfs(i, 1);
                }
                if (!result) break;
            }
            sb.append(result ? "YES" : "NO").append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int departure, int color) {
        colors[departure] = color;
        for (int next : arr[departure]) {
            if (colors[next] == color) {
                result = false;
                return;
            }
            if (colors[next] == 0) {
                if (color == 1) dfs(next, -1);
                else if (color == -1) dfs(next, 1);
            }
        }
    }
}
