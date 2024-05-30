import java.util.*;

class Solution {
    static final int START = 0, TIME = 1;
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[START] - o2[START]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[TIME] - o2[TIME]);
        
        int currentTime = 0;
        int totalWaitTime = 0;
        int jobIndex = 0;
        int jobCount = jobs.length;
        
        while (jobIndex < jobCount || !pq.isEmpty()) {
            while (jobIndex < jobCount && jobs[jobIndex][START] <= currentTime) {
                pq.offer(jobs[jobIndex]);
                jobIndex++;
            }
            
            if (pq.isEmpty()) {
                currentTime = jobs[jobIndex][START];
            } else {
                int[] currentJob = pq.poll();
                currentTime += currentJob[TIME];
                totalWaitTime += currentTime - currentJob[START];
            }
        }
        
        return totalWaitTime / jobCount;
    }
}
