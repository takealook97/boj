class Solution {
    public static int solution(int[] money) {
        int N = money.length;
        if (N == 0) return 0;
        if (N == 1) return money[0];

        int[] dp1 = new int[N];
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        for (int i = 2; i < N - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + money[i]);
        }

        int[] dp2 = new int[N];
        dp2[0] = 0;
        dp2[1] = money[1];
        for (int i = 2; i < N; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + money[i]);
        }

        int[] dp3 = new int[N];
        dp3[0] = 0;
        dp3[1] = money[1];
        for (int i = 2; i < N - 1; i++) {
            dp3[i] = Math.max(dp3[i - 1], dp3[i - 2] + money[i]);
        }

        return Math.max(Math.max(dp1[N - 2], dp2[N - 1]), dp3[N - 2]);
    }
}
