import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                if (j == M) {
                    list.add(sc.nextInt() + "x");
                } else {
                    list.add(Integer.toString(sc.nextInt()));
                }
            }
            int count = 1;
            while (true) {
                int max = 0;
                for (int j = 0; j < list.size(); j++) {//list 내 max 정하기
                    if (max <= list.get(j).charAt(0)) {
                        max = list.get(j).charAt(0);
                    }
                }
                if (list.get(0).charAt(0) == max) {
                    if (list.get(0).length() > 1) {
                        System.out.println(count);
                        break;
                    } else {
                        list.remove(0);
                        count++;
                    }
                } else {
                    list.add(list.get(0));
                    list.remove(0);
                }
            }
        }
    }
}