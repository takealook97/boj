import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int[][] arr;
    static boolean[] visit;
    static int INF = 987654321;
    static int result = INF;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        arr = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                arr[i][j] = INF;
            }
        }
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a][b] = c;
        }
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if(i == j) {
                    result = Math.min(result, arr[i][j]);
                }
            }
        }
        if(result == INF) System.out.println(-1);
        else System.out.println(result);
    }
}
