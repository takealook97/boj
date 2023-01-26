import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int N = sc.nextInt();
        int standard = 0;
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            if (num > standard) {
                for (int j = standard + 1; j <= num; j++) {
                    stack.push(j);
                    list.add("+");
                }
                standard = num;
            } else if (stack.peek() != num) {
                System.out.println("NO");
                System.exit(0);
            }
            stack.pop();
            list.add("-");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}