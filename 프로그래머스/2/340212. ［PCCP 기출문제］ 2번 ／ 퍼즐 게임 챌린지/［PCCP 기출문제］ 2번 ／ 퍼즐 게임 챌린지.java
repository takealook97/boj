import java.util.*;

class Solution {
    static int N, timePrev;
    static int[] diffs, times;
    static long limit, level = 0;
    
    static int answer = 0;
    static final long MAX = 1_000_000_000_000_000L;
    
    public int solution(int[] diffs, int[] times, long limit) {
        N = diffs.length;
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        long lo = 1, hi = MAX, mid;
        long sum;
        
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            
            sum = getSum(mid);
            
            if(-1 < sum && sum <= limit) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }        
        
        return (int) lo;
    }
    
    static long getSum (long level) {
        timePrev = 0;
        long sum = 0;
        
        for (int i = 0; i < N; i++) {
            if (diffs[i] <= level) {
               sum += times[i]; 
            } else {
                sum += (diffs[i] - level) * (times[i] + timePrev) + times[i];
            }
            
            if(limit < sum) {
                return -1;
            }
            
            timePrev = times[i];
        }
        
        return sum;
    }
}
