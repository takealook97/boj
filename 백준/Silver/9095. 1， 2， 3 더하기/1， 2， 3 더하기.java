import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num = 0;
        for (int i = 0; i < n; i++) {
            num = sc.nextInt();
            if (num > 3) {
                int[] arr = new int[num + 1];
                arr[1] = 1;
                arr[2] = 2;
                arr[3] = 4;
                for (int j = 4; j <= num; j++) {
                    arr[j] = arr[j - 1] + arr[j - 2] + arr[j - 3];
                }
                System.out.println(arr[num]);
            } else if (num == 1) System.out.println(1);
            else if (num == 2) System.out.println(2);
            else if (num == 3) System.out.println(4);
        }
    }
}