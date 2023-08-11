import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Point> homes = new ArrayList<>();
	static ArrayList<Point> chickens = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> distanceList; // home to chicken
	static boolean[] visit;
	static int answer;

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
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					homes.add(new Point(i, j));
				} else if (num == 2) {
					chickens.add(new Point(i, j));
				}
			}
		}

		getDistance();
		answer = Integer.MAX_VALUE;
		visit = new boolean[chickens.size()];
		dfs(0, 0);

		System.out.println(answer);
	}

	static void getDistance() {
		distanceList = new ArrayList<>();
		for (Point home : homes) {
			ArrayList<Integer> distances = new ArrayList<>();
			for (Point chicken : chickens) {
				int distance = Math.abs(home.y - chicken.y) + Math.abs(home.x - chicken.x);
				distances.add(distance);
			}
			distanceList.add(distances);
		}
	}

	static void dfs(int start, int count) {
		if (count == M) {
			int sum = 0;
			for (int i = 0; i < homes.size(); i++) {
				int minDistance = Integer.MAX_VALUE;
				for (int j = 0; j < chickens.size(); j++) {
					if (visit[j]) {
						minDistance = Math.min(minDistance, distanceList.get(i).get(j));
					}
				}
				sum += minDistance;
			}
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			visit[i] = true;
			dfs(i + 1, count + 1);
			visit[i] = false;
		}
	}
}
