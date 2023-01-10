import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashMap<Integer, String> arr = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int age = sc.nextInt();
            String name = sc.next();
            if (arr.containsKey(age)) {
                arr.put(age, arr.get(age) + " " + name);
            } else {
                arr.put(age, name);
            }
        }
        int age = 0;
        while (!arr.isEmpty()) {
            if (arr.containsKey(age)) {
                StringTokenizer st = new StringTokenizer(arr.get(age), " ");
                while (st.hasMoreTokens()) {
                    System.out.println(age + " " + st.nextToken());
                }
                arr.remove(age);

            }
            age++;
        }
    }
}