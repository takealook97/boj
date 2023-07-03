import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static ArrayList<Integer> arr = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(arr);

		int left = 0;
		int right = arr.size() - 1;
		int count = 0;
		while (left < right) {
			if (arr.get(left) + arr.get(right) >= M) {
				count++;
				left++;
				right--;
			} else {
				left++;
			}
		}
		System.out.println(count);
	}
}
