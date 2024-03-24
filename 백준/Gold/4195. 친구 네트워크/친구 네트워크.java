import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int T, F;
	static HashMap<String, Integer> nameMap = new HashMap<>();
	static int[] parent, countArr;
	static final int MAX_SIZE = 200000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		parent = new int[MAX_SIZE + 1];
		countArr = new int[MAX_SIZE + 1];

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int idx = 0;

		while (T-- > 0) {
			make();
			nameMap.clear();
			Arrays.fill(countArr, 1);

			F = Integer.parseInt(br.readLine());
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine());
				String nameA = st.nextToken();
				String nameB = st.nextToken();
				if (!nameMap.containsKey(nameA)) {
					nameMap.put(nameA, idx++);
				}
				if (!nameMap.containsKey(nameB)) {
					nameMap.put(nameB, idx++);
				}

				int aIdx = nameMap.get(nameA);
				int bIdx = nameMap.get(nameB);

				union(aIdx, bIdx);
				sb.append(countArr[find(aIdx)]).append("\n");
			}
		}

		System.out.print(sb);
	}

	static void make() {
		for (int i = 1; i <= MAX_SIZE; i++) {
			parent[i] = i;
		}
	}

	static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			parent[y] = x;
			countArr[x] += countArr[y];
			return true;
		}
		return false;
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
}
