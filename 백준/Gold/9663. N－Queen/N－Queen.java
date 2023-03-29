import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int[] arr;
    static int[] temp;
    static boolean[] visit;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N + 1];
        temp = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }
        if (N == 1) System.out.println(1);
        else if (N < 4) System.out.println(0);
        else {
            dfs(0);
            System.out.println(count);
        }
    }

    static void dfs(int depth) {
        if (depth == N) {
            count++;
        }
        for (int i = 1; i <= N; i++) {
            if (!visit[i] && check(i, depth)) {
                visit[i] = true;
                temp[depth] = arr[i];
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }

    static boolean check(int number, int depth) {
        for (int i = 0; i < depth; i++) {
            int length = Math.abs(depth - i);
            int gap = Math.abs(number - temp[i]);
            if (length == gap) return false;
        }
        return true;
    }
}
