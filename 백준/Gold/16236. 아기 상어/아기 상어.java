import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int size = 2;
    static int stomach = 0;
    static int result = 0;

    static class Node {
        int y;
        int x;
        int distance;

        public Node(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        Queue<Node> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    queue.add(new Node(i, j, 0));
                    arr[i][j] = 0;
                }
            }
        }

        while (true) {
            ArrayList<Node> preys = new ArrayList<>();
            int[][] distance = new int[N][N];
            while (!queue.isEmpty()) {
                Node now = queue.poll();
                int nowY = now.y;
                int nowX = now.x;
                for (int i = 0; i < 4; i++) {
                    int nextY = nowY + dy[i];
                    int nextX = nowX + dx[i];
                    if (0 <= nextY && nextY < N && 0 <= nextX && nextX < N) {
                        if (distance[nextY][nextX] == 0 && arr[nextY][nextX] <= size) {
                            distance[nextY][nextX] = distance[nowY][nowX] + 1;
                            queue.add(new Node(nextY, nextX, distance[nextY][nextX]));
                            if (1 <= arr[nextY][nextX] && arr[nextY][nextX] <= 6 && arr[nextY][nextX] < size) {
                                preys.add(new Node(nextY, nextX, distance[nextY][nextX]));
                            }
                        }
                    }
                }
            }
            if (preys.size() == 0) {
                System.out.println(result);
                System.exit(0);
            }
            Node now = preys.get(0);
            for (int i = 1; i < preys.size(); i++) {
                if (now.distance > preys.get(i).distance) {
                    now = preys.get(i);
                } else if (now.distance == preys.get(i).distance) {
                    if (now.y > preys.get(i).y) {
                        now = preys.get(i);
                    } else if (now.y == preys.get(i).y) {
                        if (now.x > preys.get(i).x) {
                            now = preys.get(i);
                        }
                    }
                }
            }
            arr[now.y][now.x] = 0;
            result += now.distance;
            stomach++;
            if (size == stomach) {
                stomach = 0;
                size++;
            }
            queue.add(now);
        }
    }
}
