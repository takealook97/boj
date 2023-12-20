import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String bomb = br.readLine();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < line.length(); i++) {
			stack.push(line.charAt(i));

			if (stack.size() >= bomb.length()) {
				boolean isPossible = true;
				for (int j = 0; j < bomb.length(); j++) {
					if (stack.get(stack.size() - bomb.length() + j) != bomb.charAt(j)) {
						isPossible = false;
						break;
					}
				}

				if (isPossible) {
					for (int j = 0; j < bomb.length(); j++) {
						stack.pop();
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (char answerChar : stack) {
			sb.append(answerChar);
		}

		if (sb.length() == 0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb);
		}
	}
}
