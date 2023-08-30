import java.io.*;
import java.util.*;

public class Main {
	static int N, startIdx;
	static int[][] costArr;
	static boolean[] visit;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		costArr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				costArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			visit = new boolean[N];
			startIdx = i;
			visit[i] = true;
			setMinCost(i, 0, 1);
		}
		System.out.println(answer);
	}

	static void setMinCost(int from, int sum, int count) {
		if (count == N) {
			if(costArr[from][startIdx] != 0) {
				answer = Math.min(answer, sum + costArr[from][startIdx]);
			}
			return;
		}

		for (int to = 0; to < N; to++) {
			if (!visit[to] && costArr[from][to] != 0) {
				visit[to] = true;
				setMinCost(to, sum + costArr[from][to], count + 1);
				visit[to] = false;
			}
		}
	}
}