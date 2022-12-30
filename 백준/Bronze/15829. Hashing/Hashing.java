import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        String word = sc.next();
        long sum = 0;
        long num = 1;
        for (int i = 0; i < L; i++) {
            sum += ((word.charAt(i) + 1 - 'a') * num) % 1234567891;
            num = (num * 31) % 1234567891;
        }
        System.out.println(sum % 1234567891);
    }
}