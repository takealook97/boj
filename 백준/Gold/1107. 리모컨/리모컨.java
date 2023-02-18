import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        for (int i = 0; i < M; i++) {
            numbers.remove(Integer.valueOf(sc.nextInt()));//보유 중인 숫자 리스트
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 1000000; i++) {
            if (check(i)) {
                list.add(i);
            }
        }
        if (!list.contains(100)) list.add(100);
        int channel = 100;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (Math.abs(N - channel) >= Math.abs(N - list.get(i))) {
                channel = list.get(i);
            }
        }
        
        if (N == 100) {
            System.out.println(0);
            System.exit(0);
        } else if (list.contains(N)) {
            System.out.println(Math.min(Math.abs(100 - N), Integer.toString(N).length()));
            System.exit(0);
        } else {
            System.out.println(Math.min(Math.abs(100 - N), Integer.toString(channel).length() + Math.abs(channel - N)));

        }
    }

    static boolean check(int input) {
        String number = Integer.toString(input);
        for (int i = 0; i < number.length(); i++) {
            int object = number.charAt(i) - 48;
            if (!numbers.contains(object)) return false;
        }
        return true;
    }
}