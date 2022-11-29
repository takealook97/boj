import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] num = new int[N];
        int end = N * (N - 1) * (N - 2);
        int[] sort = new int[end];
        int result = 0;

        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
        }

        int index = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                for (int k = j + 1; k < N; k++) {
                    sort[index] = num[i] + num[j] + num[k];
                    index++;
                }
            }
        }

        Arrays.sort(sort);

        for (int i = 0; i < end; i++) {
            if (sort[i] <= M) {
                result = sort[i];
            }
        }
        System.out.println(result);
    }
}