import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10001];
        arr[0] = 0;
        for (int i = 1; i < 10000; i++) {
            int num = 0;
            int x = i;
            for (int j = 0; j < 4; j++) {
                num += x % 10;
                if (x / 10 > 0) {
                    x /= 10;
                } else break;
            }
            arr[i] = i + num;
        }
        Arrays.sort(arr);
        out:
        for (int i = 1; i < 10000; i++) {
            for (int j = arr[i] + 1; j < arr[i + 1]; j++) {
                if (j < 10000) {
                    System.out.println(j);
                } else break out;
            }
        }
    }
}