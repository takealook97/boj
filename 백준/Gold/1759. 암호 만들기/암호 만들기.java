import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static int[] arr;
	static boolean[] visited;
	static ArrayList<Integer> alphabets = new ArrayList<>();
	static ArrayList<Integer> list = new ArrayList<>();
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[C];
		visited = new boolean[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);

		setAlphabet();
		dfs(0, 0);

		System.out.print(answer);
	}

	static void setAlphabet() {
		alphabets.add((int)'a');
		alphabets.add((int)'e');
		alphabets.add((int)'i');
		alphabets.add((int)'o');
		alphabets.add((int)'u');
	}

	static void dfs(int idx, int depth) {
		if (depth == L) {
			if (!isPossible()) {
				return;
			}
			for (int i : list) {
				answer.append((char)i);
			}
			answer.append("\n");
			return;
		}

		for (int i = idx; i < C; i++) {
			if (!visited[i]) {
				visited[i] = true;
				list.add(arr[i]);
				dfs(i, depth + 1);
				list.remove(list.size() - 1);
				visited[i] = false;
			}
		}
	}

	static boolean isPossible() {
		int count = 0;
		for (int i : alphabets) {
			count += Collections.frequency(list, i);
		}

		if (count == 0) {
			return false;
		}

		return list.size() - count >= 2;
	}
}
