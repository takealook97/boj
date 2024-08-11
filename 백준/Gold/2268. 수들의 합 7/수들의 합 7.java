import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] tree;

	static final int SUM = 0, MODIFY = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		build(arr);

		StringBuilder sb = new StringBuilder();

		int query, b, c, temp;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			query = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			if (query == MODIFY) {
				updateRec(b - 1, c, 1, 0, N - 1);
			} else if (query == SUM) {
				if (b > c) {
					temp = b;
					b = c;
					c = temp;
				}
				sb.append(queryRec(b - 1, c - 1, 1, 0, N - 1)).append("\n");
			}
		}

		System.out.print(sb);
	}

	static void build(int[] arr) {
		tree = new long[N * 4];

		buildRec(arr, 1, 0, N - 1);
	}

	static long buildRec(int[] arr, int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			return tree[node] = arr[nodeLeft];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftVal = buildRec(arr, node * 2, nodeLeft, mid);
		long rightVal = buildRec(arr, node * 2 + 1, mid + 1, nodeRight);

		return tree[node] = merge(leftVal, rightVal);
	}

	static long queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (right < nodeLeft || nodeRight < left) {
			return 0; // default value
		}

		if (left <= nodeLeft && nodeRight <= right) {
			return tree[node];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return merge(queryRec(left, right, node * 2, nodeLeft, mid),
			queryRec(left, right, node * 2 + 1, mid + 1, nodeRight));
	}

	static long updateRec(int index, int newValue, int node, int nodeLeft, int nodeRight) {
		if (index < nodeLeft || nodeRight < index) {
			return tree[node];
		}

		if (nodeLeft == nodeRight) {
			return tree[node] = newValue;
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		long leftVal = updateRec(index, newValue, node * 2, nodeLeft, mid);
		long rightVal = updateRec(index, newValue, node * 2 + 1, mid + 1, nodeRight);
		return tree[node] = merge(leftVal, rightVal);
	}

	static long merge(long left, long right) {
		return left + right;
	}
}
