import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int switchCount, studentCount;
	static boolean[] switches;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		switchCount = Integer.parseInt(br.readLine());
		switches = new boolean[switchCount];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < switchCount; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (num == 1) {
				switches[i] = true;
			}
		}
		studentCount = Integer.parseInt(br.readLine());
		for (int i = 0; i < studentCount; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			changeStatus(gender, index);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < switchCount; i++) {
			if (switches[i]) {
				sb.append(1).append(" ");
			} else {
				sb.append(0).append(" ");
			}

			if ((i + 1) % 20 == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	static void changeStatus(int gender, int index) {
		if (gender == 1) {
			int numForMultiple = 1;
			while (index * numForMultiple - 1 < switchCount) {
				switches[index * numForMultiple - 1] = !switches[index * numForMultiple - 1];
				numForMultiple++;
			}
		} else if (gender == 2) {
			int left = index - 1;
			int right = index - 1;
			while (0 <= left && right < switchCount) {
				if (switches[left] == switches[right]) {
					left--;
					right++;
				} else {
					break;
				}
			}
			if (left != right) {
				left++;
				right--;
			}
			for (int i = left; i <= right; i++) {
				switches[i] = !switches[i];
			}
		}
	}
}
