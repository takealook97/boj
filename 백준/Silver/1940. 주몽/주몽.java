import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        int N = sc.nextInt();
        int M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            arr.add(sc.nextInt());
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            int num = M - arr.get(i);
            if (arr.contains(num)) count++;
        }
        System.out.println(count / 2);
    }
}
