import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr;
	static ArrayList<Point> homes = new ArrayList<>();
	static ArrayList<Point> chickens = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> distance; // home to chicken
	static boolean[] check;
	static int result;

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
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					homes.add(new Point(i, j));
				} else if (arr[i][j] == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}

		getDistance();
		getResult();

		System.out.println(result);
	}

	static void getDistance() {
		distance = new ArrayList<>();
		for (Point home : homes) {
			ArrayList<Integer> distances = new ArrayList<>();
			for (Point chicken : chickens) {
				int sum = Math.abs(home.y - chicken.y) + Math.abs(home.x - chicken.x);
				distances.add(sum);
			}
			distance.add(distances);
		}
	}

	static void getResult() {
		result = Integer.MAX_VALUE;
		check = new boolean[chickens.size()];
		dfs(0, 0);
	}

	static void dfs(int start, int count) {
		if (count == M) {
			int sum = 0;
			for (int i = 0; i < homes.size(); i++) {
				int minDistance = Integer.MAX_VALUE;
				for (int j = 0; j < chickens.size(); j++) {
					if (check[j]) {
						minDistance = Math.min(minDistance, distance.get(i).get(j));
					}
				}
				sum += minDistance;
			}
			result = Math.min(result, sum);
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			check[i] = true;
			dfs(i + 1, count + 1);
			check[i] = false;
		}
	}
}
