import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static ArrayList<Integer> belt = new ArrayList<>();
	static ArrayList<Boolean> robot = new ArrayList<>();
	static int stage = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt.add(Integer.parseInt(st.nextToken()));
			robot.add(false);
		}
		while (true) {
			turn();
			move();
			load();
			stage++;
			checkEnd();
		}
	}

	static void turn() {
		belt.add(0, belt.remove(2 * N - 1));
		robot.add(0, robot.remove(2 * N - 1));
		unload();
	}

	static void move() {
		if (robot.get(2 * N - 1) && !robot.get(0) && belt.get(0) != 0) {
			robot.set(0, true);
			robot.set(2 * N - 1, false);
			belt.set(0, belt.get(0) - 1);
		}
		for (int i = 2 * N - 2; i >= 0; i--) {
			if (robot.get(i) && !robot.get(i + 1) && belt.get(i + 1) != 0) {
				robot.set(i + 1, true);
				robot.set(i, false);
				belt.set(i + 1, belt.get(i + 1) - 1);
			}
		}
		unload();

	}

	static void load() {
		if (belt.get(0) != 0) {
			robot.set(0, true);
			belt.set(0, belt.get(0) - 1);
		}
	}

	static void unload() {
		if (robot.get(N - 1)) {
			robot.set(N - 1, false);
		}
	}

	static void checkEnd() {
		if (Collections.frequency(belt, 0) >= K) {
			System.out.println(stage);
			System.exit(0);
		}
	}
}
