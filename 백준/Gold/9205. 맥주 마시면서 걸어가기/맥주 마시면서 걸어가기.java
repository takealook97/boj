import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int t, n;
	static Point[] arr;
	static int[][] distArr;

	static final int LIMIT = 1000;
	static final int INF = 987654321;

	static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (t-- > 0) {
			n = Integer.parseInt(br.readLine());
			arr = new Point[n + 2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			arr[n + 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			setDistArr();

			sb.append(isPossible() ? "happy" : "sad").append("\n");
		}
		System.out.print(sb);
	}

	static void setDistArr() {
		distArr = new int[n + 2][n + 2];
		for (int i = 0; i < n + 1; i++) {
			for (int j = i + 1; j < n + 2; j++) {
				int distance = getDist(arr[i], arr[j]);
				distArr[i][j] = distance <= LIMIT ? distance : INF;
				distArr[j][i] = distArr[i][j];
			}
		}
	}

	static int getDist(Point from, Point to) {
		return Math.abs(from.y - to.y) + Math.abs(from.x - to.x);
	}

	static boolean isPossible() {
		for (int i = 0; i < n + 2; i++) {
			for (int j = 0; j < n + 2; j++) {
				for (int k = 0; k < n + 2; k++) {
					distArr[j][k] = Math.min(distArr[j][k], distArr[j][i] + distArr[i][k]);
				}
			}
		}
		return distArr[0][n + 1] < INF;
	}
}
