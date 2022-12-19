import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print(cal(sc.nextInt()));
    }

    public static int cal(int num) {
        int count = 0;
        if (num < 100) {
            return num;
        }
        else {
            count = 99;
            for (int i = 100; i <= num; i++) {
                int a = i / 100;
                int b = i / 10 % 10;
                int c = i % 10;
                if (a - b == b - c) {
                    count++;
                }
            }
        }
        return count;
    }

}