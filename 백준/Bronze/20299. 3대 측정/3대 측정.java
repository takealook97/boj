import java.io.*;
import java.util.*;

public class Main {
	static final int PEOPLE_COUNT = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int count = 0;
		out:
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int[] team = new int[PEOPLE_COUNT];
			int sum = 0;
			for (int i = 0; i < PEOPLE_COUNT; i++) {
				team[i] = Integer.parseInt(st.nextToken());
				if (team[i] < L) {
					continue out;
				}
				sum += team[i];
			}
			if (sum >= K) {
				count++;
				for (int i = 0; i < PEOPLE_COUNT; i++) {
					sb.append(team[i]).append(" ");
				}
			}
		}
		System.out.println(count);
		System.out.println(sb);
	}
}
