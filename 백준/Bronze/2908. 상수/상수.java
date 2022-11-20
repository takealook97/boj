import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();

        int a = A % 10;
        int b = (A / 10) % 10;
        int c = A / 100;
        int d = B % 10;
        int e = (B / 10) % 10;
        int f = B / 100;

        int X = a * 100 + b * 10 + c;
        int Y = d * 100 + e * 10 + f;

        if(X>Y) System.out.println(X);
        else System.out.println(Y);
    }
}
