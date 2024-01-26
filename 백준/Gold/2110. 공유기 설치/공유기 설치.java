import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, C;
	static int[] homes;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		homes = new int[N];

		for (int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(homes);

		int left = 1;
		int right = homes[N - 1] - homes[0];

		while (left <= right) {
			int mid = (left + right) / 2;
			if (isPossible(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(answer);
	}

	static boolean isPossible(int distance) {
		int count = 1;
		int lastInstall = homes[0];

		for (int i = 1; i < N; i++) {
			if (homes[i] - lastInstall >= distance) {
				count++;
				lastInstall = homes[i];
			}
		}

		return count >= C;
	}
}
