import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N;
	static int[] arr, versionArr, countArr;
	static int version = 1;
	static boolean flag;
	static List<Integer> list = new ArrayList<>();
	static PriorityQueue<Integer> answerPq = new PriorityQueue<>();

	static final int VERSION_NONE = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		versionArr = new int[N + 1];
		countArr = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (arr[i] == i) {
				versionArr[i] = version;
				answerPq.add(i);
			}
		}

		for (int i = 1; i <= N; i++) {
			if (versionArr[i] == 0) {
				flag = false;
				getMax(++version, i);
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(answerPq.size()).append("\n");

		while (!answerPq.isEmpty()) {
			sb.append(answerPq.poll()).append("\n");
		}

		System.out.print(sb);
	}

	static void getMax(int ver, int idx) {
		if (versionArr[idx] == VERSION_NONE) {
			versionArr[idx] = ver;
			getMax(ver, arr[idx]);
			if (flag) {
				return;
			}
			versionArr[idx] = VERSION_NONE;
		} else if (versionArr[idx] == ver) {// 동일 버전일 때
			list.clear();
			Arrays.fill(countArr, 0);
			for (int key = 1; key <= N; key++) {
				if (versionArr[key] == ver) {
					list.add(key);
					countArr[key]++;
				}
			}

			for (int val : list) {// list 순회
				countArr[arr[val]]--;
			}

			for (int count : countArr) {
				if (count != 0) {
					return;
				}
			}

			flag = true;
			answerPq.addAll(list);
		}
	}
}
