import java.io.*;
import java.util.*;

public class Main {
	static int N, d, k, c;
	static int[] sushi;
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[N + k];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sushi[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = N; i < N + k; i++) {
			sushi[i] = sushi[i - N];
		}

		System.out.println(getMax());
	}

	static int getMax() {
		List<Integer> list = new ArrayList<>();
		int start = 0;
		int end = 1;
		list.add(sushi[start]);
		int count = 1;
		int answer = 1;
		while (start <= end && end < N + k) {
			if(list.size() < k) {
				if(!list.contains(sushi[end])) {
					count++;
				}
				list.add(sushi[end]);
				answer = Math.max(answer, count);
				end++;
			} else {
				if(list.size() == k && !list.contains(c)) {
					answer = Math.max(answer, count + 1);
				}
				
				if(!list.contains(list.remove(0))) {
					count--;
				}
				start++;
				
				if(!list.contains(sushi[end])) {
					count++;
				}
				list.add(sushi[end]);
				answer = Math.max(answer, count);
				end++;
			}
		}
		return answer;
	}
}
