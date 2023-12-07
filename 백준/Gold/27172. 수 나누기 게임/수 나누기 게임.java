import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] players, scores;
	static boolean[] cards;
	static final int RANGE = 1000001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		players = new int[N];
		cards = new boolean[RANGE];
		scores = new int[RANGE];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			players[i] = Integer.parseInt(st.nextToken());
			cards[players[i]] = true;
		}

		for (int winner : players) {
			for (int loswer = winner * 2; loswer < RANGE; loswer += winner) {
				if (cards[loswer]) {
					scores[winner]++;
					scores[loswer]--;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int player : players) {
			sb.append(scores[player]).append(' ');
		}

		System.out.println(sb);
	}
}
