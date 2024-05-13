import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	static int N = 5;
	static char[][] board = new char[N][N];
	static boolean[] visited = new boolean[N * N];
	static int[] arr = new int[7];
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	static ArrayList<Integer> temp = new ArrayList<>();
	static Queue<Integer> queue = new ArrayDeque<>();
	static boolean[] tempVisited = new boolean[7];
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}

		getCount(0, 0);

		System.out.println(answer);
	}

	static void getCount(int depth, int start) {
		if (depth == 7) {
			if (isPossible()) {
				answer++;
			}
			return;
		}
		for (int i = start; i < N * N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				getCount(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}

	static boolean isPossible() {
		int Y = 0;
		for (int i : arr) {
			if (board[i / N][i % N] == 'Y') {
				Y++;
			}
		}
		if (Y > 3) {
			return false;
		}

		temp.clear();
		Arrays.fill(tempVisited, false);
		for (int i : arr) {
			temp.add(i);
		}
		int start = arr[0];
		tempVisited[0] = true;
		int count = 1;

		queue.clear();
		queue.add(start);
		while (!queue.isEmpty()) {
			int now = queue.poll();
			int x = now % N;
			int y = now / N;

			for (int i = 0; i < 4; i++) {
				int nextY = y + dy[i];
				int nextX = x + dx[i];
				if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
					int next = nextY * N + nextX;
					int idx = temp.indexOf(next);
					if (idx != -1 && !tempVisited[idx]) {
						tempVisited[idx] = true;
						queue.add(next);
						count++;
					}
				}
			}
		}
		return count == 7;
	}
}
