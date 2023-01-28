import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] num = new int[N + 1];
        num[1] = 0;
        //num[숫자] = 숫자를 만드는데 걸리는 횟수
        for (int i = 2; i < N + 1; i++) {
            num[i] = num[i - 1] + 1;
            if (i % 2 == 0 && num[i] > num[i / 2] + 1) {
                num[i] = num[i / 2] + 1;
            }
            if (i % 3 == 0 && num[i] > num[i / 3] + 1) {
                num[i] = num[i / 3] + 1;
            }
        }
        System.out.println(num[N]);
    }
}