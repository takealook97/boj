import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int count = 0;
        while (X >= 1) {
            if (X % 2 == 0) {
                X /= 2;
            } else {
                X--;
                count++;
            }
        }
        System.out.println(count);
    }
}