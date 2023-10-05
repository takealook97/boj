import java.io.*;
import java.util.*;

public class Main {
	static String T, P;
	static int[] arr;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = br.readLine();
		P = br.readLine();
		
		setArr(P);

		int idx = 0;
		for (int i = 0; i < T.length(); i++) {
			while (0 < idx && T.charAt(i) != P.charAt(idx)) {
				idx = arr[idx - 1];
			}
			if (T.charAt(i) == P.charAt(idx)) {
				if (idx == P.length() - 1) {
					sb.append(i - idx + 1).append(" ");
					count++;
					idx = arr[idx];
				} else {
					idx++;
				}
			}
		}
		
		System.out.println(count);
		System.out.println(sb);
	}

	static void setArr(String pattern) {
		arr = new int[pattern.length()];
		int idx = 0;
		for (int i = 1; i < pattern.length(); i++) {
			while (0 < idx && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = arr[idx - 1];
			}

			if (pattern.charAt(i) == pattern.charAt(idx)) {
				idx++;
				arr[i] = idx;
			}
		}
	}
}
