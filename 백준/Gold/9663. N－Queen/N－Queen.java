import java.util.*;

public class Main {
	static int N;
	static int[] col;
	static boolean[] visit;
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N + 1];
		visit = new boolean[N + 1];
		
		if (N == 1) System.out.println(1);
		else if (N < 4) System.out.println(0);
		else {
			setQueen(0);
			System.out.println(answer);
		}
	}

	static void setQueen(int row) {
		if (row == N) {
			answer++;
			return;
		}

		for (int i = 1; i <= N; i++) {
			if(!visit[i] && isPossible(i, row)) {
				visit[i] = true;
				col[row] = i;
				setQueen(row + 1);
				visit[i] = false;
			}
		}
	}

	static boolean isPossible(int num, int row) {
		for (int i = 0; i < row; i++) {
			if (Math.abs(row - i) == Math.abs(num - col[i])) {
				return false;
			}
		}
		return true;
	}
}
