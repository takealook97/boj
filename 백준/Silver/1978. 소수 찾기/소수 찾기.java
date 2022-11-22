import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] num = new int[N];
        int answer = N;

        for (int i = 0; i < N; i++) {
            num[i] = sc.nextInt();
            if (num[i] == 1) {
                answer--;
                continue;
            }
            for (int j = 2; j < num[i]; j++) {
                if (num[i] % j == 0) {
                    answer--;
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}