import java.util.*;

class Solution {
    static int N;
    static int[][] arr;
    static int answer = 0;
    
    public int solution(int[] a) {
        N = a.length;
        arr = new int[N][3];
        int minA = a[0];
        int minB = a[N - 1];
        
        for(int i = 0; i < N; i++) {
            minA = Math.min(minA, a[i]);
            arr[i][0] = minA;
            arr[i][1] = a[i];
        }
        for(int i = N - 1; i >= 0; i--) {
            minB = Math.min(minB, a[i]);
            arr[i][2] = minB;
        }
        
        for(int i = 0; i < N; i++) {
            if(arr[i][0] == arr[i][1] || arr[i][1] == arr[i][2]) {
                answer++;
            }
        }
        
        return answer;
    }
}
