import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] num = new int[10];
        int count = 0;

        for (int i = 0; i < 10; i++) {
            num[i] = sc.nextInt()%42;
        }

        Arrays.sort(num);

        for (int i = 0; i < 9; i++) {
            if (num[i] != num[i + 1]) {
                count++;
            }
        }

        System.out.println(count+1);
        sc.close();
    }
}
