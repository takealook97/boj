import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(s < n) {
            return new int[]{-1};
        }
        int[] arr = new int[n];
        int devided = s / n;
        int left = s % n;
        Arrays.fill(arr, devided);
        
        if(left > 0) {
            for(int i = n - 1; i >= 0; i--) {
                if(left == 0) break;
                arr[i]++;
                left--;
            }
        }
        
        return arr;
    }
}
