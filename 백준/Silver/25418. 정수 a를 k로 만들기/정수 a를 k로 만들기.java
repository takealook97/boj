import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int a, k;

	static class Number {
		int num, count;

		public Number(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		k = sc.nextInt();
		bfs();
	}

	static void bfs() {
		boolean[] visit = new boolean[k + 1];
		Queue<Number> queue = new LinkedList<>();
		visit[a] = true;
		queue.add(new Number(a, 0));

		while (!queue.isEmpty()) {
			Number now = queue.poll();
			if (now.num == k) {
				System.out.println(now.count);
				System.exit(0);
			} else if (now.num > k) {
				continue;
			}
			if (now.num * 2 <= k && !visit[now.num * 2]) {
				visit[now.num * 2] = true;
				queue.add(new Number(now.num * 2, now.count + 1));
			}
			if (now.num + 1 <= k && !visit[now.num + 1]) {
				visit[now.num + 1] = true;
				queue.add(new Number(now.num + 1, now.count + 1));
			}
		}
	}
}
