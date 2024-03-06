class Solution {
	public int solution(String name) {
		int answer = 0;
		int N = name.length();
		int min = N - 1;

		for (int i = 0; i < N; i++) {
			char alphabet = name.charAt(i);
			answer += Math.min(alphabet - 'A', 'Z' - alphabet + 1);

			int next = i + 1;
			while (next < N && name.charAt(next) == 'A') {
				next++;
			}

			min = Math.min(min, i + N - next + Math.min(i, N - next));
		}

		answer += min;
		return answer;
	}
}
