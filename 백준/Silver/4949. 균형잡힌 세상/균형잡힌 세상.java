import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        out:
        while (true) {
            String line = sc.nextLine();
            if (line.equals(".")) {
                break;
            }
            Stack<String> stack = new Stack<>();
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case '(':
                        stack.push("(");
                        break;
                    case '[':
                        stack.push("[");
                        break;
                    case ')':
                        if (stack.isEmpty()) {
                            System.out.println("no");
                            continue out;
                        } else if (stack.peek().equals("(")) {
                            stack.pop();
                        } else {
                            System.out.println("no");
                            continue out;
                        }
                        break;
                    case ']':
                        if (stack.isEmpty()) {
                            System.out.println("no");
                            continue out;
                        } else if (stack.peek().equals("[")) {
                            stack.pop();
                        } else {
                            System.out.println("no");
                            continue out;
                        }
                        break;
                }
            }
            if (stack.isEmpty()) {
                System.out.println("yes");
            } else System.out.println("no");
        }
    }
}