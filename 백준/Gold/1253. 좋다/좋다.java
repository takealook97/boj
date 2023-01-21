import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(sc.nextInt());
        }
        Collections.sort(list);
        int count = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            while (left < right) {
                if (list.get(left) + list.get(right) == list.get(i)) {
                    if (left != i && right != i) {
                        count++;
                        break;
                    } else if (left == i) {
                        left++;
                    } else if (right == i) {
                        right--;
                    }
                } else if (list.get(left) + list.get(right) < list.get(i)) {
                    left++;
                } else right--;
            }
        }
        System.out.println(count);
    }
}