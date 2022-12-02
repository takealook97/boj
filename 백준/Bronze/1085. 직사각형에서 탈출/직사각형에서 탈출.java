import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();
        int a = w - x;
        int b = h - y;

        //x y a b

        if (x <= y && x <= a && x <= b) {
            System.out.println(x);
        }
        else if (y <= x && y <= a && y <= b) {
            System.out.println(y);
        }
        else if (a <= x && a <= y && a <= b) {
            System.out.println(a);
        }
        else if (b <= x && b <= y && b <= a) {
            System.out.println(b);
        }
    }
}