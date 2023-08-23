import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] peopleCount;
	static int sum = 0;
	static int treeSum = 0;
	static ArrayList<Integer>[] linkArr;
	static boolean[] visitSrc;
	static boolean[] visit;
	static int M;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		linkArr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			linkArr[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		peopleCount = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			peopleCount[i] = Integer.parseInt(st.nextToken());
			sum += peopleCount[i];
		}
		for (int from = 1; from <= N; from++) {
			st = new StringTokenizer(br.readLine());
			int linkCount = Integer.parseInt(st.nextToken());
			while (linkCount-- > 0) {
				int to = Integer.parseInt(st.nextToken());
				if(!linkArr[from].contains(to)) {
					linkArr[from].add(to);
				}
				if(!linkArr[to].contains(from)) {
					linkArr[to].add(from);
				}
			}
		}
		
		visitSrc = new boolean[N + 1];
		visit = new boolean[N + 1];
		
		for(int i = 1; i <= N / 2; i++) {
			M = i;
			comb(1, 0);
		}
			
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
	
	static void comb(int idx, int depth) {
		if(depth == M) {
			if(isTree(true) && isTree(false)) {
				answer = Math.min(answer, getPopulationGap());
			}
			return;
		}
		for(int i = idx; i <= N; i++) {
			if(!visitSrc[i]) {
				visitSrc[i] = true;
				comb(idx + 1, depth + 1);
				visitSrc[i] = false;
			}
		}
	}
	
	static boolean isTree(boolean status) {
		visit = Arrays.copyOf(visitSrc, N + 1);
		int startNode = -1;
		
		for(int i = 1; i <= N; i++) {
			if(visit[i] == status) {
				startNode = i;
				break;
			}
		}
		return isAllLinked(startNode, status);
	}
	
	static boolean isAllLinked(int startNode, boolean status) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(startNode);
		treeSum = peopleCount[startNode];
		visit[startNode] = !status;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(!linkArr[now].isEmpty()) {
				for(int next : linkArr[now]) {
					if(visit[next] == status) {
						visit[next] = !status;
						treeSum += peopleCount[next];
						queue.add(next);
					}
				}
			}
		}
		for(int i = 1; i < N; i++) {
			if(visit[i] != visit[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	static int getPopulationGap() {
		int aSum = sum - treeSum;
		int bSum = treeSum;
		return Math.abs(aSum - bSum);
	}
}
