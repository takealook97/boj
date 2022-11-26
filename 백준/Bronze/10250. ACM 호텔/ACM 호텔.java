import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //층수*100+호수
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            int N = sc.nextInt();
            int a = 0;//호수
            int b = 0;//층수

            if (N % H == 0) {
                a = N / H;
            } else {
                a = N / H + 1;
            }
            if (N % H == 0) {
                b = H;
            } else b = N % H;
            System.out.println(b * 100 + a);
        }
    }
}
