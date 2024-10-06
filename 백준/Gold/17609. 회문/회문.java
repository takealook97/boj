import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String word;
	static final int TRUE = 0, SIMILAR = 1, FALSE = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			word = br.readLine();
			sb.append(isPalindrome(0, word.length() - 1, false)).append("\n");
		}

		System.out.print(sb);
	}

	static int isPalindrome(int lo, int hi, boolean isErased) {
		if (lo == hi || lo > hi) {
			if (isErased) {
				return SIMILAR;
			}

			return TRUE;
		}

		if (word.charAt(lo) == word.charAt(hi)) {
			return isPalindrome(lo + 1, hi - 1, isErased);
		}

		if (isErased) {
			return FALSE;
		}

		if (isPalindrome(lo + 1, hi, true) == SIMILAR ||
			isPalindrome(lo, hi - 1, true) == SIMILAR) {
			return SIMILAR;
		}

		return FALSE;
	}
}
