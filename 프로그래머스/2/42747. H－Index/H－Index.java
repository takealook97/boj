import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int N = citations.length;
        for (int i = 0; i < N; i++) {
            if (citations[i] >= N - i) {
                return N - i;
            }
        }
        return 0;
    }
}
