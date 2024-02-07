import java.util.*;

class Solution {
    static int N;
    static PriorityQueue<Range> pq = new PriorityQueue<>();
    static int answer = 0;
    
    static class Range implements Comparable<Range> {
        int start, end;
        
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Range range) {
           return this.end - range.end; 
        }
    }
    
    public int solution(int[][] targets) {
        N = targets.length;
        for(int[] target : targets) {
            pq.add(new Range(target[0], target[1]));
        }
        
        int pointer = 0;
        while (!pq.isEmpty()) {
            Range now = pq.poll();
            if(now.start < pointer){
                continue;
            }
            else {
                pointer = now.end;
                answer++;
            }
        }
        
        return answer;
    }
}
