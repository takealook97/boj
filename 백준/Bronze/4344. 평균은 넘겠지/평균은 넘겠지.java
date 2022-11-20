import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();


        for (int i = 0; i < C; i++) {
            int N = sc.nextInt();
            int[] score = new int[N];
            int sum = 0;
            double average = 0;
            int count = 0;

            for (int j = 0; j < N; j++) {
                score[j] = sc.nextInt();
                sum = sum + score[j];
            }
            average = sum / (double) N;
            for (int k = 0; k < N; k++) {
                if (score[k] > average) {
                    count++;
                }
            }
            double result = count / (double) N * 100;

            System.out.println(String.format("%.3f", result) + "%");
        }
    }
}
