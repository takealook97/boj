import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (st.hasMoreTokens()) { // Add this check
                    deque.add(Integer.parseInt(st.nextToken()));
                }
            }
            AC(p, deque);
        }
        System.out.println(sb);
    }
    static void AC(String p, Deque<Integer> deque) {
        boolean check = true;
        for (char order : p.toCharArray()) {
            if (order == 'R') {
                check = !check;
                continue;
            }
            if (check) {
                if (deque.pollFirst() == null) {
                    sb.append("error").append("\n");
                    return;
                }
            }
            else {
                if(deque.pollLast() == null){
                    sb.append("error").append("\n");
                    return;
                }
            }
        }
        sb.append("[");
        if (deque.size() > 0) {
            if (check) {
                sb.append(deque.pollFirst());
                while (!deque.isEmpty()) {
                    sb.append(",").append(deque.pollFirst());
                }
            } else {
                sb.append(deque.pollLast());
                while (!deque.isEmpty()) {
                    sb.append(",").append(deque.pollLast());
                }
            }
        }
        sb.append("]").append("\n");
    }
}
