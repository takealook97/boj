import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        if (N == K) {
            System.out.println(0);
            System.exit(0);
        }
        boolean[] check = new boolean[100001];
        check[N] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        int count = 0;
        while (true) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int temp = queue.remove();
                check[temp] = true;
                if (temp - 1 == K || temp + 1 == K || temp * 2 == K) {
                    System.out.println(count);
                    System.exit(0);
                }
                if (temp - 1 >= 0 && !check[temp - 1]) {
                    check[temp - 1] = true;
                    queue.add(temp - 1);
                }
                if (temp + 1 <= 100000 && !check[temp + 1]) {
                    check[temp + 1] = true;
                    queue.add(temp + 1);
                }
                if (temp * 2 <= 100000 && !check[temp * 2]) {
                    check[temp * 2] = true;
                    queue.add(temp * 2);
                }
            }
        }
    }
}