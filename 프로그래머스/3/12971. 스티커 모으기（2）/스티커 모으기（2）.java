import java.util.*;

class Solution {
    static int N;
    
    public int solution(int sticker[]) {
        N = sticker.length;
        if (N == 1) {
            return sticker[0];
        }

        int[] dpA = new int[N];
        int[] dpB = new int[N];

        dpA[0] = sticker[0];
        dpA[1] = sticker[0];
        for (int i = 2; i < N - 1; i++) {
            dpA[i] = Math.max(dpA[i - 1], dpA[i - 2] + sticker[i]);   
        }
        
        dpB[0] = 0;
        dpB[1] = sticker[1];
        for (int i = 2; i < N; i++) {
            dpB[i] = Math.max(dpB[i - 1], dpB[i - 2] + sticker[i]);
        }

        return Math.max(dpA[N - 2], dpB[N - 1]);
    }
}
