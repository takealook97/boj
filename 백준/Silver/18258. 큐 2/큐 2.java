import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        int last = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String order = st.nextToken();
            switch (order) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    queue.add(num);
                    last = num;
                    break;
                case "pop":
                    if (queue.isEmpty()) {
                        sb.append(-1);
                    } else {
                        sb.append(queue.poll());
                    }
                    sb.append("\n");
                    break;
                case "size":
                    sb.append(queue.size());
                    sb.append("\n");
                    break;
                case "empty":
                    if (queue.isEmpty()) {
                        sb.append(1);
                    } else sb.append(0);
                    sb.append("\n");
                    break;
                case "front":
                    if (queue.isEmpty()) sb.append(-1);
                    else sb.append(queue.peek());
                    sb.append("\n");
                    break;
                case "back":
                    if (queue.isEmpty()) sb.append(-1);
                    else sb.append(last);
                    sb.append("\n");
                    break;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}