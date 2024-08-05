import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	static int T, K, length;
	static String W;
	static Map<Character, List<Integer>> map = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			W = br.readLine();
			K = Integer.parseInt(br.readLine());
			length = W.length();

			if (K == 1) {
				sb.append("1 1\n");
				continue;
			}
			
			map.clear();
			for (int i = 0; i < length; i++) {
				char c = W.charAt(i);
				map.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
			}

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			for (List<Integer> list : map.values()) {
				if (list.size() < K) {
					continue;
				}

				for (int i = 0; i <= list.size() - K; i++) {
					int lo = list.get(i);
					int hi = list.get(i + K - 1);
					int curLength = hi - lo + 1;
					min = Math.min(min, curLength);
					max = Math.max(max, curLength);
				}
			}

			if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
				sb.append("-1\n");
			} else {
				sb.append(min).append(" ").append(max).append("\n");
			}
		}

		System.out.print(sb);
	}
}
