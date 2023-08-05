import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int S, P, A, C, G, T;
	static String password;
	static HashMap<Character, Integer> alphabet = new HashMap<>();
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		password = br.readLine();
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		alphabet.put('A', 0);
		alphabet.put('C', 0);
		alphabet.put('G', 0);
		alphabet.put('T', 0);

		int left = 0;
		int right = P - 1;

		for (int i = left; i <= right; i++) {
			alphabet.put(password.charAt(i), alphabet.get(password.charAt(i)) + 1);
		}
		check();

		for (int i = 1; i <= S - P; i++) {
			alphabet.put(password.charAt(left), alphabet.get(password.charAt(left)) - 1);
			left++;
			right++;
			alphabet.put(password.charAt(right), alphabet.get(password.charAt(right)) + 1);
			check();
		}
		System.out.println(count);
	}

	static void check() {
		if (alphabet.get('A') >= A && alphabet.get('C') >= C && alphabet.get('G') >= G && alphabet.get('T') >= T) {
			count++;
		}
	}
}
