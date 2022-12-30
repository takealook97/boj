import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> deque = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                deque.removeLast();
            } else {
                deque.addLast(num);
            }
        }
        int result = 0;
        int size = deque.size();
        if (deque.isEmpty()) {
            System.out.println(0);
        } else {
            while (true) {
                if (size == 0) {
                    break;
                }
                result += deque.peekFirst();
                deque.removeFirst();
                size--;
            }
            System.out.println(result);
        }
    }
}