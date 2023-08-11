import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static ArrayList<Integer>[] list;
	static boolean[] isExist;
	static boolean[] visit;
	static final int ALPABET_COUNT = 26;
	static StringBuilder answer = new StringBuilder();

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[ALPABET_COUNT];
		isExist = new boolean[ALPABET_COUNT];
		visit = new boolean[ALPABET_COUNT];
		for (int i = 0; i < ALPABET_COUNT; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			String[] info = br.readLine().trim().split(" ");
			int V = info[0].charAt(0) - 'A';
			int L = info[1].charAt(0) - 'A';
			int R = info[2].charAt(0) - 'A';
			isExist[V] = true;
			list[V].add(L);
			list[V].add(R);

			if (L >= 0) {
				isExist[L] = true;
				visit[L] = true;
			}
			if (R >= 0) {
				isExist[R] = true;
				visit[R] = true;
			}
		}

		traversal();

		System.out.println(answer);
	}

	static void traversal() {
		int ancestorNode = setAncestorNode();

		visit = new boolean[ALPABET_COUNT];
		getPreOrder(ancestorNode);
		answer.append("\n");

		visit = new boolean[ALPABET_COUNT];
		getInOrder(ancestorNode);
		answer.append("\n");

		visit = new boolean[ALPABET_COUNT];
		getPostOrder(ancestorNode);
	}

	static int setAncestorNode() {
		for (int i = 0; i < ALPABET_COUNT; i++) {
			if (isExist[i] != visit[i]) {
				return i;
			}
		}
		return -1;
	}

	static void getPreOrder(int node) {// VLR
		if (!list[node].isEmpty()) {
			visit[node] = true;
			answer.append((char) (node + 'A'));

			if (list[node].get(0) >= 0) {
				getPreOrder(list[node].get(0));
			}

			if (list[node].get(1) >= 0) {
				getPreOrder(list[node].get(1));
			}
		} else {
			visit[node] = true;
			answer.append((char) (node + 'A'));
		}
	}

	static void getInOrder(int node) {// LVR
		if (!list[node].isEmpty()) {
			if (list[node].get(0) >= 0) {
				getInOrder(list[node].get(0));
			}

			visit[node] = true;
			answer.append((char) (node + 'A'));

			if (list[node].get(1) >= 0) {
				getInOrder(list[node].get(1));
			}
		} else {
			visit[node] = true;
			answer.append((char) (node + 'A'));
		}
	}

	static void getPostOrder(int node) {// LRV
		if (!list[node].isEmpty()) {
			if (list[node].get(0) >= 0) {
				getPostOrder(list[node].get(0));
			}

			if (list[node].get(1) >= 0) {
				getPostOrder(list[node].get(1));
			}

			visit[node] = true;
			answer.append((char) (node + 'A'));
		} else {
			visit[node] = true;
			answer.append((char) (node + 'A'));
		}
	}
}
