import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, curAtk;
	static int[][] arr;

	static final int MONSTER = 1, HEAL = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		curAtk = Integer.parseInt(st.nextToken());

		arr = new int[N][3];
		long end = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			if (arr[i][0] == MONSTER) {
				end += (long)arr[i][1] * arr[i][2];
			}
		}

		long lo = 1, hi = end, mid;
		while (lo <= hi) {
			mid = lo + (hi - lo) / 2;
			if (isClear(mid)) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println(lo);
	}

	static boolean isClear(long maxHp) {
		long curHp = maxHp;
		long curAtkTemp = curAtk;

		for (int i = 0; i < N; i++) {
			if (arr[i][0] == MONSTER) {
				long monsterHp = arr[i][2];
				long monsterAtk = arr[i][1];

				long turnsToKillMonster = (monsterHp + curAtkTemp - 1) / curAtkTemp;
				long turnsToKillHero = (curHp + monsterAtk - 1) / monsterAtk;

				if (turnsToKillMonster > turnsToKillHero) {
					return false;
				}

				curHp -= (turnsToKillMonster - 1) * monsterAtk;
			} else if (arr[i][0] == HEAL) {
				curAtkTemp += arr[i][1];
				curHp = Math.min(maxHp, curHp + arr[i][2]);
			}
		}

		return true;
	}
}
