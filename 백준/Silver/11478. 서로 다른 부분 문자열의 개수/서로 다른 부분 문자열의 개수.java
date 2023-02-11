import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length() + 1; j++) {
                set.add(input.substring(i, j));
            }
        }
        System.out.println(set.size());
    }
}