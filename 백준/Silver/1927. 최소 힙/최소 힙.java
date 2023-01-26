import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            if (num != 0) {
                queue.add(num);
            } else {
                if (queue.isEmpty()) System.out.println(0);
                else System.out.println(queue.poll());
            }
        }
    }
}