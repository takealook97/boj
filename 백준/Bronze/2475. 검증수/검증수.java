import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] num = new int[5];
        int M = 0;
        for (int i = 0; i < 5; i++) {
            num[i] = sc.nextInt();
            int N = 0;
            N = num[i] * num[i];
            M = (M + N) % 10;
        }
        System.out.println(M);
    }
}
