import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, Q;
	static long[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		build(arr);

		StringBuilder sb = new StringBuilder();

		int x, y, a, b, temp;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			if (x > y) {
				temp = x;
				x = y;
				y = temp;
			}

			sb.append(queryRec(x - 1, y - 1, 1, 0, N - 1)).append("\n");
			updateRec(a - 1, b, 1, 0, N - 1);
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
