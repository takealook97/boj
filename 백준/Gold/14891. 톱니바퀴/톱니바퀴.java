import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static Saw[] arr = new Saw[4];

    static class Saw {
        Deque<Boolean> deque;
        boolean left;
        boolean right;

        public Saw(Deque<Boolean> deque, boolean left, boolean right) {
            this.deque = deque;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            Deque<Boolean> deque = new ArrayDeque<>();
            boolean left = false, right = false;
            for (int j = 0; j < 8; j++) {
                int num = line.charAt(j) - '0';
                boolean target;
                target = num == 1;
                if (j == 6) {
                    left = target;
                } else if (j == 2) {
                    right = target;
                }
                deque.add(target);
            }
            arr[i] = new Saw(deque, left, right);
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int num = Integer.parseInt(st.nextToken());
            boolean dir = num == 1;
            turn(index, dir);
        }
        System.out.println(getScore());
    }

    static void turn(int index, boolean dir) {//true 시계 | false 반시계
        boolean[] dirArr = new boolean[4];
        boolean[] isChangeable = new boolean[4];
        dirArr[index] = dir;
        isChangeable[index] = true;
        int cur = index;
        int toLeft = cur - 1;
        while (0 <= toLeft) {
            if (arr[toLeft].right != arr[cur].left) {
                isChangeable[toLeft] = true;
                dirArr[toLeft] = !dirArr[cur];
                cur--;
                toLeft--;
            } else break;
        }
        cur = index;
        int toRight = cur + 1;
        while (toRight < 4) {
            if (arr[cur].right != arr[toRight].left) {
                isChangeable[toRight] = true;
                dirArr[toRight] = !dirArr[cur];
                cur++;
                toRight++;
            } else break;
        }

        for (int i = 0; i < 4; i++) {
            if (isChangeable[i]) {
                if (dirArr[i]) {
                    arr[i].deque.addFirst(arr[i].deque.removeLast());
                } else {
                    arr[i].deque.addLast(arr[i].deque.removeFirst());
                }
                ArrayList<Boolean> temp = new ArrayList<>(arr[i].deque);
                arr[i].left = temp.get(6);
                arr[i].right = temp.get(2);
            }
        }
    }

    static int getScore() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            if (Boolean.TRUE.equals(arr[i].deque.peek())) {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }
}
