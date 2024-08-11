import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] minTree, maxTree;

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
			sb.append(minRec(left - 1, right - 1, 1, 0, N - 1)).append(" ")
				.append(maxRec(left - 1, right - 1, 1, 0, N - 1)).append("\n");
		}

		System.out.print(sb);
	}

	static void build(int[] arr) {
		minTree = new int[N * 4];
		maxTree = new int[N * 4];

		buildMinRec(arr, 1, 0, N - 1);
		buildMaxRec(arr, 1, 0, N - 1);
	}

	static int buildMinRec(int[] arr, int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			return minTree[node] = arr[nodeLeft];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		int leftVal = buildMinRec(arr, node * 2, nodeLeft, mid);
		int rightVal = buildMinRec(arr, node * 2 + 1, mid + 1, nodeRight);

		return minTree[node] = getMin(leftVal, rightVal);
	}

	static int buildMaxRec(int[] arr, int node, int nodeLeft, int nodeRight) {
		if (nodeLeft == nodeRight) {
			return maxTree[node] = arr[nodeLeft];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		int leftVal = buildMaxRec(arr, node * 2, nodeLeft, mid);
		int rightVal = buildMaxRec(arr, node * 2 + 1, mid + 1, nodeRight);

		return maxTree[node] = getMax(leftVal, rightVal);
	}

	static int minRec(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (right < nodeLeft || nodeRight < left) {
			return Integer.MAX_VALUE; // default value
		}

		if (left <= nodeLeft && nodeRight <= right) {
			return minTree[node];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return getMin(minRec(left, right, node * 2, nodeLeft, mid),
			minRec(left, right, node * 2 + 1, mid + 1, nodeRight));
	}

	static int maxRec(int left, int right, int node, int nodeLeft, int nodeRight) {
		if (right < nodeLeft || nodeRight < left) {
			return Integer.MIN_VALUE; // default value
		}

		if (left <= nodeLeft && nodeRight <= right) {
			return maxTree[node];
		}

		int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
		return getMax(maxRec(left, right, node * 2, nodeLeft, mid),
			maxRec(left, right, node * 2 + 1, mid + 1, nodeRight));
	}

	static int getMin(int left, int right) {
		return Math.min(left, right);
	}

	static int getMax(int left, int right) {
		return Math.max(left, right);
	}
}
