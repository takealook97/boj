import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split("");
		Stack<String> stack = new Stack<>();
		Stack<Integer> valueStack = new Stack<>();
		valueStack.push(0);

		for (String bracket : arr) {
			if (bracket.equals("(") || bracket.equals("[")) {
				stack.push(bracket);
				valueStack.push(0);
			} else if (!stack.isEmpty()) {
				if (bracket.equals(")") && stack.peek().equals("(")) {
					stack.pop();
					int value = valueStack.pop();
					valueStack.push(valueStack.pop() + Math.max(2 * value, 2));
				} else if (bracket.equals("]") && stack.peek().equals("[")) {
					stack.pop();
					int value = valueStack.pop();
					valueStack.push(valueStack.pop() + Math.max(3 * value, 3));
				} else {
					System.out.println(0);
					return;
				}
			} else {
				System.out.println(0);
				return;
			}
		}

		if (!stack.isEmpty()) {
			System.out.println(0);
		} else {
			System.out.println(valueStack.pop());
		}
	}
}
