import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static boolean[] check;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M];
        check = new boolean[N];
        dfs(0);
    }

    static void dfs(int depth) {
        if (depth == M) {
            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                if (result.isEmpty() || result.get(result.size() - 1) < arr[i]) {
                    result.add(arr[i]);
                }
            }
            if (result.size() == M) {
                for(int i = 0; i < M; i++){
                    System.out.print(result.get(i) + " ");
                }
                System.out.println();
            }
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                check[i] = true;
                arr[depth] = i + 1;
                dfs(depth + 1);
                check[i] = false;
            }
        }
    }
}