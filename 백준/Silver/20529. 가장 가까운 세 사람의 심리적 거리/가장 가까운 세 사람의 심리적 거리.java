import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Info> list = new ArrayList<>();

	static class Info implements Comparable<Info> {
		ArrayList<String> arr;
		int sum;

		public Info(ArrayList<String> arr, int sum) {
			this.arr = arr;
			this.sum = sum;
		}

		@Override
		public int compareTo(Info o) {
			return this.sum - o.sum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		makeList();
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			ArrayList<String> mbti = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				mbti.add(st.nextToken());
			}
			int min = Integer.MAX_VALUE;
			for (Info info : list) {
				ArrayList<String> temp = new ArrayList<>(mbti);
				if (temp.contains(info.arr.get(0))) {
					temp.remove(info.arr.get(0));
					if (temp.contains(info.arr.get(1))) {
						temp.remove(info.arr.get(1));
						if (temp.contains(info.arr.get(2))) {
							temp.remove(info.arr.get(2));
							min = info.sum;
							break;
						}
					}
				}
			}
			sb.append(min).append("\n");
		}
		System.out.print(sb);
	}

	static void makeList() {
		String[] mbti = {"ISTJ", "ISFJ", "INFJ", "INTJ", "ISTP", "ISFP", "INFP", "INTP", "ESTP", "ESFP", "ENFP", "ENTP",
			"ESTJ", "ESFJ", "ENFJ", "ENTJ"};
		for (int i = 0; i < mbti.length; i++) {
			for (int j = 0; j < mbti.length; j++) {
				for (int k = 0; k < mbti.length; k++) {
					ArrayList<String> arr = new ArrayList<>();
					arr.add(mbti[i]);
					arr.add(mbti[j]);
					arr.add(mbti[k]);
					list.add(new Info(arr, getDistSum(mbti[i], mbti[j], mbti[k])));
				}
			}
		}
		Collections.sort(list);
	}

	static int getDistSum(String a, String b, String c) {
		return getDist(a, b) + getDist(b, c) + getDist(a, c);
	}

	static int getDist(String a, String b) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if (a.charAt(i) != b.charAt(i)) {
				count++;
			}
		}
		return count;
	}
}