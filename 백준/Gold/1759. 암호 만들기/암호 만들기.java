import java.util.*;
import java.io.*;

public class Main {
	static int L, C;
	static char[] alphabetArr;
	static boolean[] visit;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		alphabetArr = new char[C];
		visit = new boolean[C];

		String line = br.readLine().trim().replace(" ", "");
		for (int i = 0; i < C; i++) {
			alphabetArr[i] = line.charAt(i);
		}
		Arrays.sort(alphabetArr);

		dfs(0, "", 0);
		
		System.out.print(answer);
		
	}

	static void dfs(int idx, String password, int depth) {
		if (depth >= L) {
			if (isPossible(password)) {
				answer.append(password).append("\n");
			}
			return;
		}
		for (int i = idx; i < C; i++) {
			if (!visit[i]) {
				visit[i] = true;
				StringBuilder updated = new StringBuilder();
				updated.append(password).append(alphabetArr[i]);
				dfs(i + 1, updated.toString(), depth + 1);
				visit[i] = false;
			}
		}
	}

	static boolean isPossible(String password) {
		int vowelCount = 0;
		int consonantCount = 0;
		char[] charArr = password.toCharArray();
		for (char alphabet : charArr) {
			if (alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u') {
				vowelCount++;
			} else {
				consonantCount++;
			}
		}
		return vowelCount >= 1 && consonantCount >= 2;
	}
}
