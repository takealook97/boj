import java.util.*;

public class Main {
    static int N;
    static int K;
    static int result = 100000;

    static class Node {
        int position;
        int time;

        public Node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        bfs();
        System.out.println(result);
    }

    static void bfs() {
        boolean[] visit = new boolean[100001];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(N, 0));
        visit[N] = true;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int nowP = now.position;
            int nowT = now.time;
            visit[nowP] = true;
            if(nowP == K) result = Math.min(result, nowT);
            if(nowP * 2 <= 100000 && !visit[nowP * 2]) queue.add(new Node(nowP * 2, nowT));
            if(nowP + 1 <= 100000 && !visit[nowP + 1]) queue.add(new Node(nowP + 1, nowT + 1));
            if(nowP - 1 >= 0 && !visit[nowP - 1]) queue.add(new Node(nowP - 1, nowT + 1));
        }
    }
}