import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] count = new int[101];
    static int[] arr = new int[101];
    static boolean[] check = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a] = b;
        }
        bfs();
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        count[1] = 0;
        check[1] = true;

        while (!queue.isEmpty()) {
            int destination = queue.poll();
            if (destination == 100) {
                System.out.println(count[destination]);
                return;
            }

            for (int i = 1; i < 7; i++) {
                int movable = destination + i;
                if (100 < movable) continue;
                if (check[movable]) continue;
                check[movable] = true;

                if (arr[movable] != 0) {
                    if (!check[arr[movable]]) {
                        queue.offer(arr[movable]);
                        check[arr[movable]] = true;
                        count[arr[movable]] = count[destination] + 1;
                    }
                } else {
                    queue.offer(movable);
                    count[movable] = count[destination] + 1;
                }
            }
        }
    }
}