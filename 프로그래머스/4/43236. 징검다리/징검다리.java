import java.util.*;

class Solution {
    static long answer = 0;
    
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        return lowerBound(distance, rocks, n);
    } 
    
    static int lowerBound(int distance, int[] rocks, int n) {
        int lo = 1;
        int hi = distance;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            int pos = 0;
            
            // mid 유지 판단
            for (int rock : rocks) {
                if (rock - pos < mid) {
                    count++;
                } else {
                    pos = rock;
                }
            }
            
            // 마지막점
            if (distance - pos < mid) {
                count++;
            }
            
            if (count <= n) {
                answer = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return (int) answer;
    }
}
