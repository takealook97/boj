import java.util.*;
import java.io.*;

/**
 * @author 김건우
 *
 *
 * 1. 게리맨더링 -> 50개의 test 3초
 * 2. N 개 마을을 2개 지역구로 분리
 * 3. 정확하게 두개의 트리로 구분되어야 한다.
 * 4. 2 개 지역구 유권자 수 차이의 최소값을 출력
 * 5. 4 <= N <= 8
 * 6. 유권자 수 0 <= Pi <= 20
 * 7. 인접할경우 1, 아닐경우 0
 * 8. 간선 방향 X
 *
 */

public class Main {
	static int N;
	static ArrayList<Integer>[] listArr;//간선 정보 인접리스트
	static int[] population;
	static boolean[] visit;
	static int allSum, targetCount;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		listArr = new ArrayList[N + 1];
		population = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<>();
		}

		allSum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			allSum += population[i];
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int count = Integer.parseInt(st.nextToken());
			for (int j = 0; j < count; j++) {
				listArr[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		answer = Integer.MAX_VALUE;//answer 초기화

		//마을은 모두 이어져있다. -> 1부터 시작
		for (int i = 1; i <= N / 2; i++) {
			if (answer == 0) {
				break;
			}
			targetCount = i;
			visit = new boolean[N + 1];
			getMinGap(1, 0, 0);
		}
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	//조합을 통해 visit을 해버리고 visit이 아닌애들끼리 묶이는지 확인
	static void getMinGap(int now, int sum, int depth) {
		if (depth == targetCount) {
			//true인 애들과 false인 애들 각각 비교
			boolean isTruePassed = false;
			boolean isFalsePassed = false;
			for (int i = 1; i <= N; i++) {
				if (visit[i]) {
					isTruePassed = isLinked(i, true);
					break;
				}
			}
			for (int i = 1; i <= N; i++) {
				if (!visit[i]) {
					isFalsePassed = isLinked(i, false);
					break;
				}
			}
			if (isTruePassed && isFalsePassed) {
				answer = Math.min(answer, Math.abs((allSum - sum) - sum));
			}
			return;
		}
		for (int i = now; i <= N; i++) {
			if (!visit[i]) {
				visit[i] = true;
				getMinGap(i + 1, sum + population[i], depth + 1);
				visit[i] = false;
			}
		}
	}

	static boolean isLinked(int idx, boolean status) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(idx);
		boolean[] tempVisit = Arrays.copyOf(visit, N + 1);
		tempVisit[idx] = !status;
		while (!queue.isEmpty()) {
			int now = queue.poll();
			if (!listArr[now].isEmpty()) {
				for (int next : listArr[now]) {
					if (tempVisit[next] == status) {
						tempVisit[next] = !status;
						queue.add(next);
					}
				}
			}
		}
		for (int i = 1; i < N; i++) {
			if (tempVisit[i] != tempVisit[i + 1]) {
				return false;
			}
		}
		return true;
	}
}