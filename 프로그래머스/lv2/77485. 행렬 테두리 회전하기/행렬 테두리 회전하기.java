import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
	static int[][] arr;
	static int N;
	static Queue<Integer> queue;
	static PriorityQueue<Integer> pq;
	static int[] result;

	public int[] solution(int rows, int columns, int[][] queries) {
		makeArr(rows, columns);
		N = queries.length;
		result = new int[N];
		for (int i = 0; i < N; i++) {
			result[i] = rotate(queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1);
		}
		return result;
	}

	static void makeArr(int r, int c) {
		arr = new int[r][c];
		int num = 1;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				arr[i][j] = num++;
			}
		}
	}

	static int rotate(int y1, int x1, int y2, int x2) {
		queue = new LinkedList<>();
		pq = new PriorityQueue<>();
		int min = Integer.MAX_VALUE;

		queue.add(arr[y1 + 1][x1]);
		for (int i = x1; i < x2; i++) {
			queue.add(arr[y1][i]);
		}
		for (int i = y1; i < y2; i++) {
			queue.add(arr[i][x2]);
		}
		for (int i = x2; i > x1; i--) {
			queue.add(arr[y2][i]);
		}
		for (int i = y2; i > y1 + 1; i--) {
			queue.add(arr[i][x1]);
		}

		for (int i = x1; i < x2; i++) {
			int num = queue.poll();
			arr[y1][i] = num;
			min = Math.min(min, num);
		}
		for (int i = y1; i < y2; i++) {
			int num = queue.poll();
			arr[i][x2] = num;
			min = Math.min(min, num);
		}
		for (int i = x2; i > x1; i--) {
			int num = queue.poll();
			arr[y2][i] = num;
			min = Math.min(min, num);
		}
		for (int i = y2; i > y1; i--) {
			int num = queue.poll();
			arr[i][x1] = num;
			min = Math.min(min, num);
		}
		return min;
	}
}