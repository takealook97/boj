import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] num = new int[3];

        for (int i = 0; i < 3; i++) {
            num[i] = sc.nextInt();
        }
        Arrays.sort(num);

        for (int i = 0; i < 3; i++) {
            System.out.print(num[i] + " ");
        }
    }
}