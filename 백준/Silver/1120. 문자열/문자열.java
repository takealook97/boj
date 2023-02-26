import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String A = sc.next();
        String B = sc.next();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i <= B.length() - A.length(); i++) {
            String temp = B.substring(i, i + A.length());
            int count = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j) != temp.charAt(j)) {
                    count++;
                }
            }
            arr.add(count);
        }
        Collections.sort(arr);
        System.out.println(arr.get(0));
    }
}