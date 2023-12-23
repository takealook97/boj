import java.io.*;
import java.util.*;

class Solution {
    static final int MOD = 1000000007;
    
    public int solution(int n, int[] arr) {
        List<Integer> money = new ArrayList<>();
        for(int i : arr) {
            money.add(i);
        }
        Collections.sort(money);
        
        while(true) {
            if(money.get(money.size() - 1) > n) {
                money.remove(money.size() - 1);
            } else {
                break;
            }
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i : money) {
            for (int j = i; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - i]) % MOD;
            }
        }
        
        return dp[n];
    }
}
