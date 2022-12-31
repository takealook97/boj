import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int arrNum = 0;
        int num = 665;
        int length = 0;
        while (true) {
            num++;
            int number = 0;
            number = num;
            int a = 1000;
            for (int i = 3; i <= 7; i++) {
                if (number / a == 0) {
                    length = i;
                    break;
                } else a *= 10;
            }
            for (int i = 0; i <= length - 3; i++) {
                if (number % 1000 == 666) {
                    arr[arrNum] = num;
                    arrNum++;
                    break;
                } else {
                    number /= 10;
                }
            }
            if (arr[N - 1] != 0) {
                break;
            }
        }
        System.out.println(arr[N - 1]);
    }
}