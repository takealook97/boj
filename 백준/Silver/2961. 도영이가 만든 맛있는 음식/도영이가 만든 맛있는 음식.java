import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] taste;
	static boolean[] visit;
	static int minGap = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		taste = new int[N][2];
		visit = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			taste[i][0] = Integer.parseInt(st.nextToken());
			taste[i][1] = Integer.parseInt(st.nextToken());
		}
		getMinGap(0);
		System.out.println(minGap);
	}

	static void getMinGap(int index) {
		if (index == N) {
			int sourness = 1;
			int bitterness = 0;
			for (int i = 0; i < N; i++) {
				if (visit[i]) {
					sourness *= taste[i][0];
					bitterness += taste[i][1];
				}
			}
			if (sourness == 1 && bitterness == 0) {
				return;
			}
			minGap = Math.min(minGap, Math.abs(sourness - bitterness));
			return;
		}
		visit[index] = true;
		getMinGap(index + 1);
		visit[index] = false;
		getMinGap(index + 1);
	}
}
