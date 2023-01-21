import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashSet<Integer> list = new HashSet<>();
        for (int i = 0; i < N; i++) {
            list.add(sc.nextInt());
        }
        int M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            if (list.contains(sc.nextInt())) {
                System.out.print("1 ");
            } else System.out.print("0 ");
        }
    }
}