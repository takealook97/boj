import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Deque<Integer> deque = new LinkedList<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] result = new int[N];
        for (int i = 1; i <= N; i++) {
            deque.addLast(i);
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M - 1; j++) {
                int num = 0;
                num = deque.peekFirst();
                deque.removeFirst();
                deque.addLast(num);
            }
            result[i - 1] = deque.peekFirst();
            deque.removeFirst();
        }
        System.out.print("<");
        for(int i = 0; i < N-1; i++){
            System.out.print(result[i]+","+" ");
        }
        System.out.print(result[N-1]);
        System.out.print(">");
    }
}