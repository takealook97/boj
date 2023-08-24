import java.io.*;
import java.util.*;

public class Main {
	static int N, d, k, c;
	static int[] sushi;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		System.out.println(getMax());
	}

	static int getMax() {
		int answer = 0;
		int[] checkArr = new int[d + 1];
		int typeCount = 0;

		for (int i = 0; i < k; i++) {
			if (checkArr[sushi[i]] == 0) {
				typeCount++;
			}
			checkArr[sushi[i]]++;
		}
		answer = typeCount;

		for (int i = 1; i < N; i++) {
			if (checkArr[sushi[i - 1]] > 0) {
				checkArr[sushi[i - 1]]--;
				if (checkArr[sushi[i - 1]] == 0) {
					typeCount--;
				}
			}

			if (checkArr[sushi[(i + k - 1) % N]] == 0) {
				typeCount++;
			}
			checkArr[sushi[(i + k - 1) % N]]++;

			if (checkArr[c] == 0) {
				answer = Math.max(answer, typeCount + 1);
			} else {
				answer = Math.max(answer, typeCount);
			}

			if(answer == k + 1) {
				return answer;
			}
		}
		return answer;
	}
}
