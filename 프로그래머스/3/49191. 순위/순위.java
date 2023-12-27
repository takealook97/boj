import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        int[][] board = new int[n + 1][n + 1];
        for(int[] arr : results) {
            board[arr[0]][arr[1]] = 1;
        }
        
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n; j++) {
                for(int k = 0; k <= n; k++) {
                    if(board[j][i] == 1 && board[i][k] == 1) {
                        board[j][k] = 1;
                    }
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            int count = 0; 
            for(int j = 1; j <= n; j++){
                if(board[i][j] == 1 || board[j][i] == 1) {
                    count++;
                }
            }
            
            if(count == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
}