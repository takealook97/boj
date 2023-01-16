import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(sc.next());
        }
        int count = 0;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String name = sc.nextLine();
            if (set.contains(name)) {
                list.add(name);
                count++;
            }
        }
        Collections.sort(list);
        System.out.println(count);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}