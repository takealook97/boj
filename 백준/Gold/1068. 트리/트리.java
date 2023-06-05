import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] parent;
    static ArrayList<Integer>[] arr;
    static ArrayList<Integer> leaves = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
        }
        int target = Integer.parseInt(br.readLine());

        deleteNodes(target);

        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }
        int start = -3;
        for (int i = 0; i < N; i++) {
            if (parent[i] != -1 && parent[i] != -2) {
                arr[parent[i]].add(i);
            } else if (parent[i] == -1) {
                start = i;
            }
        }
        if (start < 0) {
            System.out.println(0);
            System.exit(0);
        }
        bfs(start);
        System.out.println(leaves.size());
    }

    static void deleteNodes(int index) {
        parent[index] = -2;
        for (int i = 0; i < N; i++) {
            if (parent[i] == index) {
                deleteNodes(i);
            }
        }
    }

    static void bfs(int num) {
        boolean[] visit = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);
        visit[num] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (arr[now].isEmpty()) {
                leaves.add(now);
            }
            for (int next : arr[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
