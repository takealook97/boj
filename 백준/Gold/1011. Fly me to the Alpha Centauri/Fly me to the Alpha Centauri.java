import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int maxCount = (int)Math.sqrt(y - x);
			if (maxCount == Math.sqrt(y - x)) {
				sb.append(maxCount * 2 - 1);
			} else if (y - x <= maxCount * maxCount + maxCount) {
				sb.append(maxCount * 2);
			} else {
				sb.append(maxCount * 2 + 1);
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
