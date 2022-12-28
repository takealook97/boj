import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = 1;
        int b = 1;
        while (true) {
            a = sc.nextInt();
            b = sc.nextInt();
            if (a == 0) {
                break;
            } else if (b % a == 0) {
                System.out.println("factor");
            } else if (a % b == 0) {
                System.out.println("multiple");
            } else System.out.println("neither");
        }
    }
}