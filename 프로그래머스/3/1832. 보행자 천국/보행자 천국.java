import java.io.*;
import java.util.*;

class Solution {
	static int[][][] dp;
	static final int MOD = 20170805;

	public int solution(int n, int m, int[][] cityMap) {
		dp = new int[n + 1][m + 1][2];
		dp[1][1][0] = dp[1][1][1] = 1;

		for(int i = 1 ; i <= n; i++){
			for(int j = 1 ; j <= m; j++){
				if(cityMap[i - 1][j - 1] == 0){
					dp[i][j][0] += (dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD;
					dp[i][j][1] += (dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD;
				} else if(cityMap[i - 1][j - 1] == 1){
					dp[i][j][0] = 0;
					dp[i][j][1] = 0;
				} else {
					dp[i][j][0] = dp[i - 1][j][0];
					dp[i][j][1] = dp[i][j - 1][1];
				}
			}
		}
		return dp[n][m][0];
	}
}
