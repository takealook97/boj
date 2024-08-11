import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static long[] tree;

	static final char UPDATE = 'C', MULTIPLY = 'P';

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		do {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int[] arr = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				int num = Integer.parseInt(st.nextToken());
				if (num > 0) {
					arr[i] = 1;
				} else if (num < 0) {
					arr[i] = -1;
				}
			}

			build(arr);

			char query;
			int a, b;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				query = st.nextToken().charAt(0);
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());

				if (query == MULTIPLY) {
					long result = queryRec(a - 1, b - 1, 1, 0, N - 1);
					char answer;
					if (result > 0) {
						answer = '+';
					} else if (result < 0) {
						answer = '-';
					} else {
						answer = '0';
					}
					sb.append(answer);
				} else if (query == UPDATE) {
					if (b > 0) {
						b = 1;
					} else if (b < 0) {
						b = -1;
					}

					updateRec(a - 1, b, 1, 0, N - 1);
				}
			}

			sb.append("\n");
		} while (br.ready());

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
			return 1; // default value
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
		return left * right;
	}
}
