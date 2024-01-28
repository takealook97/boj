import java.util.*;

class Solution {
    static int N;
    static int answer = 0;
    
    public int solution(int[][] triangle) {
        N = triangle.length;
        if(N == 1) {
            return triangle[0][0];
        }
        
        int[] before;
        int[] now = null;
        
        for(int i = 1; i < N; i++) {
            before = triangle[i - 1];
            now = triangle[i];
            
            now[0] += before[0];
            now[now.length - 1] += before[before.length - 1];
            for(int j = 1; j < now.length - 1; j++) {
                if(before[j - 1] >= before[j]) {
                    now[j] += before[j - 1];
                } else {
                    now[j] += before[j];
                }
            }
            triangle[i] = now;
        }
        
        for(int i : now) {
            answer = Math.max(answer, i);
        }
        
        return answer;
    }
}
