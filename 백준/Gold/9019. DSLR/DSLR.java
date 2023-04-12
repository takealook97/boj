import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int result;
    static String[] order;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            result = Integer.parseInt(st.nextToken());
            order = new String[10000];
            visit = new boolean[10000];
            bfs(num);
        }
        System.out.print(sb);
    }

    static void bfs(int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(num);
        visit[num] = true;
        order[num] = "";
        while (!queue.isEmpty()) {
            int number = queue.poll();
            if (visit[result]) {
                sb.append(order[result]).append("\n");
                return;
            }
            if (!visit[D(number)]) {
                queue.add(D(number));
                visit[D(number)] = true;
                order[D(number)] = order[number] + "D";
            }
            if (!visit[S(number)]) {
                queue.add(S(number));
                visit[S(number)] = true;
                order[S(number)] = order[number] + "S";
            }
            if (!visit[L(number)]) {
                queue.add(L(number));
                visit[L(number)] = true;
                order[L(number)] = order[number] + "L";
            }
            if (!visit[R(number)]) {
                queue.add(R(number));
                visit[R(number)] = true;
                order[R(number)] = order[number] + "R";
            }
        }
    }

    static int D(int num) {
        return num * 2 % 10000;
    }

    static int S(int num) {
        if (num == 0) {
            return 9999;
        }
        return num - 1;
    }

    static int L(int num) {
        return (num % 1000) * 10 + num / 1000;
    }

    static int R(int num) {
        return (num % 10) * 1000 + num / 10;
    }
}
