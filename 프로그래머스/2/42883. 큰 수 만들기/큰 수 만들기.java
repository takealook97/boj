class Solution {
	public String solution(String number, int k) {
		char[] numbers = number.toCharArray();
		int length = numbers.length - k;
		int start = 0;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char max = '0';
			for (int j = start; j <= i + k; j++) {
				if (numbers[j] > max) {
					max = numbers[j];
					start = j + 1;
				}
			}
			sb.append(max);
		}

		return sb.toString();
	}
}
