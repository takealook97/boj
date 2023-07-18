import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long count = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] - B >= 0) {
				arr[i] -= B;
			} else {
				arr[i] = 0;
			}
			count++;
		}
		for (int i : arr) {
			if (i > 0) {
				int added = i / C;
				i %= C;
				if (i > 0) {
					added++;
				}
				count += added;
			}
		}
		System.out.println(count);
	}
}
