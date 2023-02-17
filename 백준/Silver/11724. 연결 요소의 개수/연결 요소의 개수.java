import java.util.Scanner;

public class Main {

    static int v;
    static int e;
    static int[][] graph = new int[1001][1001];
    static boolean[] visited = new boolean[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a][b] = graph[b][a] = 1;
        }
        int result = 0;
        for (int i = 1; i <= v; i++) {
            if (!visited[i]) {
                dfs(i);
                result++;
            }
        }
        System.out.println(result);
        sc.close();
    }

    public static void dfs(int index) {
        if (!visited[index]) {
            visited[index] = true;
            for (int i = 1; i <= v; i++) {
                if (graph[index][i] == 1) {
                    dfs(i);
                }
            }
        }
    }
}