import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] tree;

	static final int UPDATE = 1, SUM = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}

		build(arr);

		StringBuilder sb = new StringBuilder();

		int query, b;
		long c;
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			query = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Long.parseLong(st.nextToken());

			if (query == UPDATE) {
				updateRec(b - 1, c, 1, 0, N - 1);
			} else if (query == SUM) {
				long sum = queryRec(b - 1, (int)c - 1, 1, 0, N - 1);
				sb.append(sum).append("\n");
			}
		}

		System.out.print(sb);
	}

	static void build(long[] arr) {
		tree = new long[N * 4];

		buildRec(arr, 1, 0, N - 1);
	}

	static long buildRec(long[] arr, int node, int nodeLeft, int nodeRight) {
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

	static long updateRec(int index, long newValue, int node, int nodeLeft, int nodeRight) {
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
