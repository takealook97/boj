import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, I, M;
	static Map<Integer, Point> map = new HashMap<>();
	static int answer = 0;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		I = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int idx = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			map.put(idx++, new Point(y, x));
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 1; k < I / 2; k++) {
					findMaxCount(i, j, k, I / 2 - k);
				}
			}
		}

		System.out.println(answer);
	}

	private static void findMaxCount(int i, int j, int xLength, int yLength) {
		int temp = 0;
		for (int k = 0; k < M; k++) {
			if (map.get(j).y <= map.get(k).y && map.get(k).y <= map.get(j).y + yLength &&
				map.get(i).x <= map.get(k).x && map.get(k).x <= map.get(i).x + xLength) {
				temp++;
			}
		}
		answer = Math.max(answer, temp);
	}
}
