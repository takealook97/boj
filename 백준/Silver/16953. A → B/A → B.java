import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(9);

        int count = 1;
        while (B != A) {
            if (B < A || list.contains(B % 10)) {
                System.out.println(-1);
                System.exit(0);
            } else if (B % 2 == 0) {
                B /= 2;
                count++;
            } else if (B % 10 == 1) {
                B /= 10;
                count++;
            }
        }
        System.out.println(count);
    }
}