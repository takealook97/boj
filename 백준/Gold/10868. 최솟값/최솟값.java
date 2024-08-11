import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		build(arr);

		StringBuilder sb = new StringBuilder();
		int left, right;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			left = Integer.parseInt(st.nextToken());
			right = Integer.parseInt(st.nextToken());
			sb.append(queryRec(left - 1, right - 1, 1, 0, N - 1)).append("\n");
		}

		System.out.print(sb);
	}

	static void build(int[] arr) {
		tree = new int[N * 4];

		buildRec(arr, 1, 0, N - 1);
	}

	static int buildRec(int[] arr, int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			return tree[node] = arr[nodeLeft];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		int leftVal = buildRec(arr, node * 2, nodeLeft, mid);
		int rightVal = buildRec(arr, node * 2 + 1, mid + 1, nodeRight);

		return tree[node] = getMin(leftVal, rightVal);
	}

	static int queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (right < nodeLeft || nodeRight < left) {
			return Integer.MAX_VALUE; // default value
		}

		if (left <= nodeLeft && nodeRight <= right) {
			return tree[node];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return getMin(queryRec(left, right, node * 2, nodeLeft, mid),
			queryRec(left, right, node * 2 + 1, mid + 1, nodeRight));
	}

	static int getMin(int left, int right) {
		return Math.min(left, right);
	}
}
