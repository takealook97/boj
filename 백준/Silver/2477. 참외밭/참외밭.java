import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int[] direction = new int[6];
        int[] length = new int[6];
        for (int i = 0; i < 6; i++) {
            direction[i] = sc.nextInt();
            length[i] = sc.nextInt();
        }
        int W = 0;
        int H = 0;
        int w = 0;
        int h = 0;

        for (int i = 0; i < 6; i++) {
            if (W < length[i]) {
                W = length[i];
            }
        }
        for (int i = 0; i < 6; i++) {
            if (length[i] == W) {
                if (length[(i + 1) % 6] > (length[(i + 5) % 6])) {
                    H = length[(i + 1) % 6];
                    w = length[(i + 3) % 6];
                    h = length[(i + 4) % 6];
                } else {
                    H = (length[(i + 5) % 6]);
                    w = length[(i + 3) % 6];
                    h = length[(i + 2) % 6];
                }
            }
        }
        System.out.println((W * H - w * h) * K);
    }
}