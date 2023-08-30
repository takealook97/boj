import java.io.*;
import java.util.*;

public class Main {
	static char[] arr;
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		arr = sc.nextLine().toCharArray();
		StringBuilder answer = new StringBuilder();
		
		for (char now : arr) {
			if ('A' <= now && now <= 'Z') {
				answer.append(now);
			} else if (now == '(') {
				stack.add(now);
			} else if (now == ')') {
				while (!stack.isEmpty()) {
					char tempNow = stack.pop();
					if(tempNow == '(') {
						break;
					}
					answer.append(tempNow);
				}
			} else {
				while (!stack.isEmpty() && getRank(stack.peek()) >= getRank(now)) {
					answer.append(stack.pop());
				}
				stack.add(now);
			}
		}
		
		while (!stack.isEmpty()) {
			answer.append(stack.pop());
		}
		
		System.out.println(answer);
	}

	static int getRank(char temp) {
		if (temp == '(') {
			return 1;
		} else if (temp == '+' || temp == '-') {
			return 2;
		} else {
			return 3;
		}
	}
}
