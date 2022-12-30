import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int open = 0;
            int close = 0;
            String input = sc.next();
            int[] check = new int[input.length()];
            int sum = 0;
            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) - 40 == 0) {
                    check[j] = -1;
                    open++;
                } else {
                    check[j] = 1;
                    close++;
                }
            }
            if (open == close && input.charAt(0) - 40 == 0 && input.charAt(input.length() - 1) - 40 == 1) {
                for (int j = 0; j < input.length(); j++) {
                    sum += check[j];
                    if (sum > 0) {
                        System.out.println("NO");
                        break;
                    }
                }
                if (sum == 0) {
                    System.out.println("YES");
                }
            } else System.out.println("NO");
        }
    }
}