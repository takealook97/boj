import java.util.*;

class Solution {
    static int N, M;
    static int[][] board;
    static final int MOD = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        board = new int[n + 1][m + 1];
        board[1][1] = 1;
        if(puddles.length > 0) {
            for(int[] puddle : puddles) {
                if(puddle.length < 2) {
                    break;
                }
                board[puddle[1]][puddle[0]] = -1;
            }
        }
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(board[i][j] == 0) {
                    if(board[i - 1][j] < 0 && board[i][j - 1] >= 0) {
                        board[i][j] = board[i][j - 1];
                    } else if(board[i - 1][j] >= 0 && board[i][j - 1] < 0) {
                        board[i][j] = board[i - 1][j];
                    } else if(board[i - 1][j] >= 0 && board[i][j - 1] >= 0) {
                        board[i][j] = (board[i - 1][j] + board[i][j - 1]) % MOD;
                    }
                }
            }
        }
        
        return board[n][m];
    }
}