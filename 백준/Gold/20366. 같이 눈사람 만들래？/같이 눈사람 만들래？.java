import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		for (int loA = 0; loA < N - 3; loA++) {
			for (int hiA = loA + 3; hiA < N; hiA++) {
				int loB = loA + 1;
				int hiB = hiA - 1;

				while (loB < hiB) {
					int snowmanA = arr[loA] + arr[hiA];
					int snowmanB = arr[loB] + arr[hiB];
					int gap = Math.abs(snowmanA - snowmanB);

					if (gap < answer) {
						answer = gap;
					}

					if (snowmanA > snowmanB) {
						loB++;
					} else {
						hiB--;
					}
				}
			}
		}

		System.out.println(answer);
	}
}
