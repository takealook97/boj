import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] player;
	static boolean[] visit;
	static ArrayList<Integer> orderList;
	static int idx = 1;
	static boolean[] isExist;
	static final int PLAYER_COUNT = 9;
	static final int GROUND_COUNT = 3;

	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		player = new int[N][PLAYER_COUNT + 1];
		visit = new boolean[PLAYER_COUNT + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= PLAYER_COUNT; j++) {
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<Integer> order = new ArrayList<>();
		visit[1] = true;
		setOrder(order);
		System.out.println(answer);
	}

	static void setOrder(ArrayList<Integer> order) {
		if (order.size() == PLAYER_COUNT - 1) {
			order.add(3, 1);
			orderList = order;
			answer = Math.max(answer, getScore());

			order.remove(3);
			return;
		}
		for (int i = 2; i <= PLAYER_COUNT; i++) {
			if (!visit[i]) {
				visit[i] = true;
				order.add(i);
				setOrder(order);
				order.remove(order.size() - 1);
				visit[i] = false;
			}
		}
	}

	static int getScore() {
		int scoreSum = 0;
		idx = 0;
		for (int inning = 0; inning < N; inning++) {
			isExist = new boolean[GROUND_COUNT + 1];
			int outCount = 0;
			while (outCount < 3) {
				int hit = player[inning][orderList.get(idx)];
				if (hit == 0) {
					outCount++;
				} else {
					scoreSum += calculateScore(hit);
					move(hit);
				}

				idx++;
				if (idx >= PLAYER_COUNT) {
					idx -= PLAYER_COUNT;
				}
			}
		}
		return scoreSum;
	}

	static int calculateScore(int hit) {
		int score = 0;
		for (int i = GROUND_COUNT; i >= GROUND_COUNT + 1 - hit; i--) {
			if (isExist[i]) {
				score++;
				isExist[i] = false;
			}
		}
		if (hit == 4) {
			score++;
		}
		return score;
	}

	static void move(int hit) {
		for (int i = GROUND_COUNT - hit; i >= 1; i--) {
			if (isExist[i]) {
				isExist[i + hit] = true;
				isExist[i] = false;
			}
		}
		if (hit <= 3) {
			isExist[hit] = true;
		}
	}
}
