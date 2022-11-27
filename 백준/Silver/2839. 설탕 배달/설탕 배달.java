import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int result = 0;
        for (int i = 0; i <= N / 3; i++) {
            for (int j = 0; j <= N / 5; j++) {
                if (N == 5 * j + 3 * i) {
                    result = j + i;
                    System.out.println(result);
                    return;
                }
            }
        }
        for (int i = 0; i <= N / 3; i++) {
            for (int j = 0; j <= N / 5; j++) {
                if (N != 5 * j + 3 * i) {
                    System.out.println(-1);
                    return;
                }
            }
        }
    }
}