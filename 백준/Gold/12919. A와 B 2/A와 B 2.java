import java.util.Scanner;

public class Main {
	static String S, T;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = sc.next();
		T = sc.next();
		System.out.println(setPossible(S, T));
	}

	public static int setPossible(String now, String target) {
		if (now.length() == target.length()) {
			return now.equals(target) ? 1 : 0;
		}

		int answer = 0;

		if (target.charAt(0) == 'B') {
			StringBuilder sb = new StringBuilder(target.substring(1));
			answer += setPossible(now, sb.reverse().toString());
		}

		if (target.charAt(target.length() - 1) == 'A') {
			answer += setPossible(now, target.substring(0, target.length() - 1));
		}

		return answer > 0 ? 1 : 0;
	}
}
