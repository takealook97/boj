import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] parent;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int[][] arr;
    static ArrayList<Integer>[] list;
    static boolean[] visit;
    static int total = 0;


    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        arr = new int[N][N];
        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        int temp = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                int alphabet = line.charAt(j);
                if (alphabet >= 96) {
                    alphabet -= 96;
                } else if (alphabet >= 65) {
                    alphabet -= 38;
                } else alphabet -= 48;
                arr[i][j] = alphabet;
                if (N == 1) temp = arr[i][j];
                if (arr[i][j] != 0) {
                    list[i].add(j);
                    list[j].add(i);
                    total += arr[i][j];
                    pq.add(new Edge(i, j, arr[i][j]));
                }
            }
        }
        if (N == 1) {
            System.out.println(temp);
            System.exit(0);
        }
        if (!isConnected()) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(getResult());
    }

    static boolean isConnected() {
        int start = 0;
        out:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) {
                    start = i;
                    break out;
                }
            }
        }

        visit = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visit[start] = true;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : list[now]) {
                if (!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
        for (boolean num : visit) {
            if (!num) return false;
        }
        return true;
    }

    static int getResult() {
        int req = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int y = find(edge.from);
            int x = find(edge.to);
            if (y != x) {
                req += edge.weight;
                union(edge.from, edge.to);
            }
        }
        return total - req;
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}
