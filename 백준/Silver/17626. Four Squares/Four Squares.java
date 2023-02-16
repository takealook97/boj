import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        int check = (int) Math.sqrt(n);
        for (int i = 0; i <= check; i++) {
            list.add(i * i);
        }
        Collections.sort(list);
        if (Math.sqrt(n) % 1 == 0) {//1개 일 경우
            if (list.contains(check * check)) {
                System.out.println(1);
            }
        } else {//2개 이상일 경우
            int tailIndex = check;
            while (tailIndex > 0) {//2개일 경우
                if (Math.sqrt(n - list.get(tailIndex)) % 1 == 0) {
                    System.out.println(2);
                    System.exit(0);
                } else tailIndex--;
            }
            for (int j = 1; j <= check; j++) {//3개일 경우
                for (int k = j; k <= check; k++) {
                    int temp = n - list.get(j) - list.get(k);
                    if (Math.sqrt(temp) % 1 == 0) {
                        System.out.println(3);
                        System.exit(0);
                    }
                }
            }
            System.out.println(4);
        }
    }
}