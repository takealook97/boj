import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long[] arrX = new long[N + 1];
        long[] arrY = new long[N + 1];
        for (int i = 0; i < N; i++) {
            arrX[i] = sc.nextInt();
            arrY[i] = sc.nextInt();
        }
        arrX[N] = arrX[0];
        arrY[N] = arrY[0];
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < N; i++) {
            sum1 += (arrX[i] * arrY[i + 1]);
            sum2 += (arrX[i + 1] * arrY[i]);
        }
        System.out.printf("%.1f", Math.abs(sum1 - sum2) / 2);
    }
}