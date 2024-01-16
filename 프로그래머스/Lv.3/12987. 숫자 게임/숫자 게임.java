import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> pqA = new PriorityQueue<>();
        PriorityQueue<Integer> pqB = new PriorityQueue<>();
        for(int i = 0; i < A.length; i++) {
            pqA.add(A[i]);
            pqB.add(B[i]);
        }
        
        int answer = 0;
        while(!pqA.isEmpty() && !pqB.isEmpty()) {
            if(pqA.peek() >= pqB.peek()) {
                pqB.poll();
            } else {
                pqA.poll();
                pqB.poll();
                answer++;
            }
        }
        
        return answer;
    }
}