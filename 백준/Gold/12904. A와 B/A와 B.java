import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		System.out.println(TtoS(s, t) ? 1 : 0);
	}

	static boolean TtoS(String s, String t) {
		while (s.length() < t.length()) {
			char lastChar = t.charAt(t.length() - 1);
			if (lastChar == 'A') {
				t = t.substring(0, t.length() - 1);
			} else {
				t = new StringBuilder(t.substring(0, t.length() - 1)).reverse().toString();
			}
		}
		return s.equals(t);
	}
}
