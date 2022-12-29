import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int length = 1;
        for (int i = 1; i <= 10; i++) {
            if (num % Math.pow(10, i) == num) {
                length = i;
                break;
            }
        }

        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = num % 10;
            num /= 10;
        }
        Arrays.sort(arr);
        for (int i = length - 1; i >= 0; i--) {
            System.out.print(arr[i]);
        }
    }
}