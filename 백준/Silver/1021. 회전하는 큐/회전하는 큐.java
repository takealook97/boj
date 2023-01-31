import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] arr = new int[M];
        Deque<Integer> deque = new LinkedList<>();
        Deque<Integer> sub = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }
        int count = 0;
        for (int i = 0; i < M; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < M; i++) {
            sub.clear();
            sub.addAll(deque);
            if (deque.peekFirst() == arr[i]) {
                deque.pollFirst();
                continue;
            }
            int front = 0;
            for (int j = 0; j < sub.size(); j++) {
                if (sub.peekFirst() == arr[i]) {
                    front = j;
                    break;
                } else sub.addLast(sub.pollFirst());
            }
            int behind = deque.size() - front;

            if (front <= behind) {
                for (int j = 0; j < front; j++) {
                    deque.addLast(deque.pollFirst());
                    count++;
                }
                deque.removeFirst();
            } else {
                for (int j = 0; j < behind; j++) {
                    deque.addFirst(deque.pollLast());
                    count++;
                }
                deque.removeFirst();
            }

        }
        System.out.println(count);
    }
}