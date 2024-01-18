class Solution {
    static int N;
    static int[] sumArr;

    public int solution(int[] cookie) {
        N = cookie.length;
        sumArr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sumArr[i + 1] = sumArr[i] + cookie[i];
        }

        int answer = 0;
        for (int m = 0; m < N - 1; m++) {
            int left = m, right = m + 1;
            while (left >= 0 && right < N) {
                int leftSum = sumArr[m + 1] - sumArr[left];
                int rightSum = sumArr[right + 1] - sumArr[m + 1];

                if (leftSum == rightSum) {
                    answer = Math.max(answer, leftSum);
                    left--;
                    right++;
                } else if (leftSum > rightSum) {
                    right++;
                } else {
                    left--;
                }
            }
        }

        return answer;
    }
}
