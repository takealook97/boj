import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] arr;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visit = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        dfs(0);
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if (depth == M) {
            if (M == 1) {
                sb.append(arr[0]).append("\n");
            } else {
                for (int i = 0; i < arr.length - 1; i++) {
                    if (arr[i] >= arr[i + 1]) {
                        return;
                    }
                }
                for (int i : arr) {
                    sb.append(i).append(" ");
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                arr[depth] = list.get(i);
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }
}