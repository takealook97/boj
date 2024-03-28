import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, T;
	static Point[] arr;
	static boolean[] visited;

	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			if (this.y == o.y) {
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.y, o.y);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new Point[N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Point(x, y);
		}

		Arrays.sort(arr);

		System.out.println(bfs());
	}

	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0));
		int count = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Point now = queue.poll();

				if (now.y >= T) {
					return count;
				}

				int start = lowerBound(now.y - 2);
				int end = upperBound(now.y + 2);

				for (int j = start; j <= end; j++) {
					if (!visited[j] && isPossible(now, arr[j])) {
						visited[j] = true;
						queue.add(arr[j]);
					}
				}
			}
			count++;
		}

		return -1;
	}

	static boolean isPossible(Point a, Point b) {
		return Math.abs(a.x - b.x) <= 2 && Math.abs(a.y - b.y) <= 2;
	}

	static int lowerBound(int value) {
		int low = 0, high = N - 1;
		while (low < high) {
			int mid = (low + high) / 2;
			if (arr[mid].y >= value) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return arr[low].y >= value ? low : N;
	}

	static int upperBound(int value) {
		int low = 0, high = N - 1;
		while (low < high) {
			int mid = (low + high + 1) / 2;
			if (arr[mid].y <= value) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		return arr[high].y <= value ? high : -1;
	}
}
