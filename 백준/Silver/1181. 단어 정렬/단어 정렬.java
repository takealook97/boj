import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] arr = new String[N];
        String[] newArr = new String[N + 1];
        int[] count = new int[51];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.next();
            for (int j = 1; j <= 50; j++) {
                if (arr[i].length() == j) {
                    count[j]++;
                }
            }
        }
        Arrays.sort(arr);
        int arrNum = 0;
        for (int i = 1; i <= 50; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[j].length() == i) {
                    newArr[arrNum] = arr[j];
                    arrNum++;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            if (!Objects.equals(newArr[i], newArr[i + 1])) {
                System.out.println(newArr[i]);
            }
        }
    }
}