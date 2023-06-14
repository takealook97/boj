import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = Arrays.stream(stones).max().getAsInt();
        int answer = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (isPossible(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    public boolean isPossible(int[] stones, int k, int maxNum) {
        int count = 0;
        for (int stone : stones) {
            if (stone - maxNum + 1 <= 0) {
                count++;
            } else {
                count = 0;
            }

            if (count >= k) {
                return false;
            }
        }
        return true;
    }
}
