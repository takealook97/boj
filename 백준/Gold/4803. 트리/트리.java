import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static ArrayList<Integer>[] arr;
    static int T;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;

            arr = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a].add(b);
                arr[b].add(a);
            }
            T = 0;
            visit = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    if (dfs(0, i)) T++;
                }
            }
            sb.append("Case ").append(index).append(": ");
            switch (T) {
                case 0:
                    sb.append("No trees.").append("\n");
                    break;
                case 1:
                    sb.append("There is one tree.").append("\n");
                    break;
                default:
                    sb.append("A forest of ").append(T).append(" trees.").append("\n");
                    break;
            }
            index++;
        }
        System.out.print(sb);
    }

    static boolean dfs(int root, int num) {
        for (int i : arr[num]) {
            if (i == root) continue;
            if (visit[i]) return false;
            visit[i] = true;
            if (!dfs(num, i)) return false;
        }
        return true;
    }
}