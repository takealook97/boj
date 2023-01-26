import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        while (N-- > 0) {
            int num = sc.nextInt();
            if (num != 0) {
                if (!map.containsKey(num)) {
                    map.put(num, 0);
                }
                map.put(num, map.get(num) + 1);
                queue.add(Math.abs(num));
            } else {
                if (!queue.isEmpty()) {
                    int key = queue.peek();
                    if (map.containsKey(-key) && map.get(-key) != 0) {
                        System.out.println(-key);
                        queue.poll();
                        map.put(-key, map.get(-key) - 1);
                    } else if (map.containsKey(key) && map.get(key) != 0) {
                        System.out.println(key);
                        queue.poll();
                        map.put(key, map.get(key) - 1);
                    }
                } else System.out.println(0);
            }
        }
    }
}