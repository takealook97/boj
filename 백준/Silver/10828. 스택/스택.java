import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String order = st.nextToken();
            switch (order) {
                case "push":
                    deque.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if (deque.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(deque.peekLast());
                        deque.removeLast();
                    }
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    if (deque.isEmpty()) System.out.println(1);
                    else System.out.println(0);
                    break;
                case "top":
                    if (deque.isEmpty()) {
                        System.out.println(-1);
                    } else System.out.println(deque.peekLast());
                    break;
            }
        }
    }
}