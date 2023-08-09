import java.io.*;
import java.util.*;

public class Main {
	static boolean[][] isExist;
	static final int MAX_LENGTH = 100;
	static final int PAPER_LENGTH = 10;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		isExist = new boolean[MAX_LENGTH][MAX_LENGTH];

		for (int paper = 0; paper < num; paper++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int i = x; i < x + PAPER_LENGTH; i++) {
				for (int j = y; j < y + PAPER_LENGTH; j++) {
					isExist[i][j] = true;
				}
			}
		}
		int sum = 0;
		for (int i = 0; i < MAX_LENGTH; i++) {
			for (int j = 0; j < MAX_LENGTH; j++) {
				if (isExist[i][j]) {
					sum++;
				}
			}
		}
		System.out.println(sum);
	}
}
