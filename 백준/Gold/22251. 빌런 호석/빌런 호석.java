import java.util.Scanner;

public class Main {
	static int N, K, P, X;
	static boolean[][] check;
	static int[][] countArr;
	static char[] XCharArr;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		P = sc.nextInt();
		X = sc.nextInt();

		check = new boolean[10][10];
		setLED();
		countArr = new int[10][10];
		setReverseCount();

		XCharArr = String.format("%0" + K + "d", X).toCharArray();

		dfs(0, 0, new char[K]);

		System.out.println(answer);
	}

	static void dfs(int idx, int count, char[] current) {
		if (idx == K) {
			int currentNumber = Integer.parseInt(new String(current));
			if (currentNumber == 0 || currentNumber > N || currentNumber == X)
				return;
			answer++;
			return;
		}

		for (int i = 0; i < 10; i++) {
			int nextCount = count + countArr[XCharArr[idx] - '0'][i];
			if (nextCount <= P) {
				current[idx] = (char)(i + '0');
				dfs(idx + 1, nextCount, current);
			}
		}
	}

	static void setLED() {
		for (int i : new int[] {0, 1, 2, 4, 5, 6}) {
			check[0][i] = true;
		}
		for (int i : new int[] {2, 5}) {
			check[1][i] = true;
		}
		for (int i : new int[] {0, 2, 3, 4, 6}) {
			check[2][i] = true;
		}
		for (int i : new int[] {0, 2, 3, 5, 6}) {
			check[3][i] = true;
		}
		for (int i : new int[] {1, 2, 3, 5}) {
			check[4][i] = true;
		}
		for (int i : new int[] {0, 1, 3, 5, 6}) {
			check[5][i] = true;
		}
		for (int i : new int[] {0, 1, 3, 4, 5, 6}) {
			check[6][i] = true;
		}
		for (int i : new int[] {0, 2, 5}) {
			check[7][i] = true;
		}
		for (int i : new int[] {0, 1, 2, 3, 4, 5, 6}) {
			check[8][i] = true;
		}
		for (int i : new int[] {0, 1, 2, 3, 5, 6}) {
			check[9][i] = true;
		}
	}

	static void setReverseCount() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == j) {
					continue;
				}
				for (int k = 0; k < 10; k++) {
					if (check[i][k] != check[j][k]) {
						countArr[i][j]++;
					}
				}
			}
		}
	}
}
