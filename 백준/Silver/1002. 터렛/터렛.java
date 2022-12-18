import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] x1 = new int[T];
        int[] y1 = new int[T];
        int[] r1 = new int[T];
        int[] x2 = new int[T];
        int[] y2 = new int[T];
        int[] r2 = new int[T];

        for (int i = 0; i < T; i++) {
            x1[i] = sc.nextInt();
            y1[i] = sc.nextInt();
            r1[i] = sc.nextInt();
            x2[i] = sc.nextInt();
            y2[i] = sc.nextInt();
            r2[i] = sc.nextInt();
        }
        double[] length = new double[T];
        for (int i = 0; i < T; i++) {
            length[i] = Math.sqrt((x1[i] - x2[i]) * (x1[i] - x2[i]) + (y1[i] - y2[i]) * (y1[i] - y2[i]));
        }
        for (int i = 0; i < T; i++) {
            if (x1[i] == x2[i] && y1[i] == y2[i] && r1[i] == r2[i]) {
                System.out.println(-1);
            } else if (length[i] == r1[i] + r2[i] || length[i] == Math.abs(r1[i] - r2[i])) {
                System.out.println(1);
            } else if (length[i] > r1[i] + r2[i] || length[i] < Math.abs(r1[i] - r2[i])) {
                System.out.println(0);
            } else System.out.println(2);
        }
    }
}