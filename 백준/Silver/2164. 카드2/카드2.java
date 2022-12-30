import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }
        for (int i = 1; i <= N; i++) {
            int a = 0;
            if (deque.size() == 1) {
                System.out.println(deque.peek());
                break;
            }
            deque.removeFirst();
            a = deque.peekFirst();
            deque.removeFirst();
            deque.addLast(a);
        }
    }
}