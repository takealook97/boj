import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			queue.add(num);
			visit[num] = true;
		}

		int count = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			count++;
			while (size-- > 0 && !queue.isEmpty()) {
				int now = queue.poll();
				int check = 1;
				while (check <= N) {
					int temp = now ^ check;
					if (temp <= N && !visit[temp]) {
						visit[temp] = true;
						queue.add(temp);
					}
					check = check << 1;
				}
			}
		}
		System.out.println(count - 1);
	}
}
