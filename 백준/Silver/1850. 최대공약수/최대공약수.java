import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        long A = sc.nextLong();
        long B = sc.nextLong();
        long a = Math.max(A, B);
        long b = Math.min(A, B);

        while (b != 0) {
            long c = a % b;
            a = b;
            b = c;
        }
        for(int i = 0; i < a; i++){
            sb.append("1");
        }
        System.out.println(sb);
    }
}
