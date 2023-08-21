import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static boolean[] visit;
	static Queue<Integer> queue = new LinkedList<>();
	static int count = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		if (N == K) {
			System.out.println(0);
			System.exit(0);
		}
		visit = new boolean[100001];
		visit[N] = true;
		queue.add(N);
		while (true) {
			count++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				visit[cur] = true;
				if (cur - 1 == K || cur + 1 == K || cur * 2 == K) {
					System.out.println(count);
					System.exit(0);
				}
				if (cur - 1 >= 0 && !visit[cur - 1]) {
					visit[cur - 1] = true;
					queue.add(cur - 1);
				}
				if (cur + 1 <= 100000 && !visit[cur + 1]) {
					visit[cur + 1] = true;
					queue.add(cur + 1);
				}
				if (cur * 2 <= 100000 && !visit[cur * 2]) {
					visit[cur * 2] = true;
					queue.add(cur * 2);
				}
			}
		}
	}
}
