import java.util.*;

class Solution {
    static int N;
    static String word;
    static char[] arr;
    static boolean flag = false;
    static int answer = 0;
    
    public int solution(String word) {
		N = word.length();
		this.word = word;
		arr = new char[]{'A', 'E', 'I', 'O', 'U' };

		dfs("");

		return answer - 1;
	}

	static void dfs(String current) {
		if (flag) return;
		if (current.length() <= 5) {
			answer++;
			if (current.equals(word)) {
				flag = true;
				return;
			}
		}
		if (current.length() == 5) return;

		for (int i = 0; i < 5; i++) {
			dfs(current + arr[i]);
		}
	}
}
