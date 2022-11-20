import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        float[] score = new float[N];
        float[] change = new float[N];

        for (int i = 0; i < N; i++) {
            score[i] = sc.nextInt();
        }

        Arrays.sort(score);

        float M = score[N - 1];
        float all = 0;

        for (int j = 0; j < N; j++) {
            change[j] = score[j] / M * 100;
            all = all + change[j];
        }

        System.out.println(all / N);
    }
}
