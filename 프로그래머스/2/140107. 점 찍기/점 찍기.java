class Solution {
    public long solution(int k, int d) {
        long answer = 0;

        for (int x = 0; x <= d; x += k) {
            int max = (int) Math.sqrt((long) d * d - (long) x * x);
            answer += (max / k) + 1;
        }

        return answer;
   }
}
