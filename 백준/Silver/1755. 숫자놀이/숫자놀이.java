import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<Number> numArr = new ArrayList<>();

	static class Number implements Comparable<Number> {
		int num;
		String word;

		public Number(int num, String word) {
			this.num = num;
			this.word = word;
		}

		@Override
		public int compareTo(Number o) {
			return this.word.compareTo(o.word);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int num = N; num <= M; num++) {
			numArr.add(new Number(num, getNum2Word(num)));
		}
		Collections.sort(numArr);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numArr.size(); i++) {
			if (i > 0 && i % 10 == 0) {
				sb.append("\n");
			}
			sb.append(numArr.get(i).num).append(" ");
		}
		System.out.println(sb);
	}

	static String getNum2Word(int num) {
		StringBuilder word = new StringBuilder();
		String numberString = Integer.toString(num);
		for (int i = 0; i < numberString.length(); i++) {
			word.append(getNum2String(numberString.charAt(i))).append(" ");
		}
		return word.toString();

	}

	static String getNum2String(char num) {
		switch (num) {
		case '0':
			return "zero";
		case '1':
			return "one";
		case '2':
			return "two";
		case '3':
			return "three";
		case '4':
			return "four";
		case '5':
			return "five";
		case '6':
			return "six";
		case '7':
			return "seven";
		case '8':
			return "eight";
		case '9':
			return "nine";
		}
		return null;
	}
}
