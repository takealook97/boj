import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();
        int round = 0;
        while (A != B) {
            A = A / 2 + A % 2;
            B = B / 2 + B % 2;
            round++;
        }
        System.out.println(round);
    }
}