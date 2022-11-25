import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int N = sc.nextInt();
            if(N == 0) break;
            if (N < 10) {
                System.out.println("yes");
            } else if (10 <= N && N < 100) {
                if (N / 10 == N % 10) {
                    System.out.println("yes");
                } else System.out.println("no");
            } else if (100 <= N && N < 1000) {
                if (N / 100 == N % 10) {
                    System.out.println("yes");
                } else System.out.println("no");
            } else if (1000 <= N && N < 10000) {
                if (N / 1000 == N % 10 && (N / 100) % 10 == (N / 10) % 10) {
                    System.out.println("yes");
                } else System.out.println("no");
            } else if (10000 <= N && N < 100000) {
                if (N / 10000 == N % 10 && (N / 1000) % 10 == (N / 10 % 10)) {
                    System.out.println("yes");
                } else System.out.println("no");
            }
        }
    }
}