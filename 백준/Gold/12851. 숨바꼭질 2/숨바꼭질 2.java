import java.util.*;

public class Main {
    static int N;
    static int K;
    static int time;
    static int count;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        bfs();
        System.out.println(time);
        System.out.println(count);

    }

    static void bfs() {
        int[] visit = new int[100001];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));
        visit[N] = 1;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            if (point.x == K) {
                if (count == 0) time = point.y;
                if (time == point.y) count++;
                continue;
            }
            int[] arr = {point.x - 1, point.x + 1, point.x * 2};
            for (int i = 0; i < 3; i++) {
                int next = arr[i];
                if (next < 0 || next > 100000) {
                    continue;
                }
                if (visit[next] == 0 || visit[next] == point.y + 1) {
                    visit[next] = point.y + 1;
                    queue.add(new Point(next, point.y + 1));
                }
            }
        }
    }
}