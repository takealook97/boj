import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt() * sc.nextInt() * sc.nextInt();
        String A = String.valueOf(X);

        for (int i = 0; i < 10; i++) {
            int sum = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j)-'0' == i) {
                    sum++;
                }
            }
            System.out.println(sum);
        }
    }
}