import java.util.*;

class Solution {
    
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int lo = 0;
        int hi = people.length - 1;
        int sum;
        
        while(lo <= hi) {
            sum = people[lo] + people[hi];
            if(sum <= limit) {
                lo++;
                hi--;
                answer++;
            } else {
                hi--;
                answer++;
            }
        }
        
        return answer;
    }
}
