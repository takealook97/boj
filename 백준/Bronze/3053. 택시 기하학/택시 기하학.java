import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double r = sc.nextInt();
        System.out.println(String.format("%.6f", Math.PI * r * r));
        System.out.println(String.format("%.6f",(r * r * 4) / 2 + (r * r * 4) % 2));
    }
}