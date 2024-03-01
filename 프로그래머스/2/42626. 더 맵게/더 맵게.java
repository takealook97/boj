import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int score : scoville) {
            pq.add((long)score);
        }
        
        long A, B;
        while(pq.peek() < K) {
            if(pq.isEmpty()) return -1;
            A = pq.poll();
            if(pq.isEmpty()) return -1;
            B = pq.poll();
            pq.add(A + (B * 2));
            answer++;
        }
        return answer;
    }
}
