import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(sc.next(), sc.next());
        }
        for (int i = 0; i < M; i++) {
            System.out.println(map.get(sc.next()));
        }
    }
}
