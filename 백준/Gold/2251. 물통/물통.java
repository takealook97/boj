import java.util.*;

public class Main {
    static int A, B, C;
    static ArrayList<Integer> result = new ArrayList<>();
    static ArrayList<Bottle> list = new ArrayList<>();

    static class Bottle {
        int a, b, c;

        public Bottle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Bottle other = (Bottle) obj;
            return a == other.a && b == other.b && c == other.c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();
        C = sc.nextInt();
        bfs();
        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Bottle> queue = new LinkedList<>();
        queue.add(new Bottle(0, 0, C));
        while (!queue.isEmpty()) {
            Bottle bottle = queue.poll();
            if(!list.contains(bottle)) list.add(bottle);
            else continue;

            int a = bottle.a;
            int b = bottle.b;
            int c = bottle.c;
            if (a == 0 && !result.contains(c)) result.add(c);

            if (a != A) {
                int gap = A - a;
                if (b != 0) {
                    if (gap > b) queue.add(new Bottle(a + b, 0, c));
                    else queue.add(new Bottle(a + gap, b - gap, c));
                }
                if (c != 0) {
                    if (gap > c) queue.add(new Bottle(a + c, b, 0));
                    else queue.add(new Bottle(a + gap, b, c - gap));
                }
            }
            if (b != B) {
                int gap = B - b;
                if (a != 0) {
                    if (gap > a) queue.add(new Bottle(0, b + a, c));
                    else queue.add(new Bottle(a - gap, b + gap, c));
                }
                if (c != 0) {
                    if (gap > c) queue.add(new Bottle(a, b + c, 0));
                    else queue.add(new Bottle(a, b + gap, c - gap));
                }
            }
            if (c != C) {
                int gap = C - c;
                if (a != 0) {
                    if (gap > a) queue.add(new Bottle(0, b, c + a));
                    else queue.add(new Bottle(a - gap, b, c + gap));
                }
                if (b != 0) {
                    if (gap > b) queue.add(new Bottle(a, 0, c + b));
                    else queue.add(new Bottle(a, b - gap, c + gap));
                }
            }
        }
    }
}
