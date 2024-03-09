import java.util.*;

class Solution {
    static int N;
    static PriorityQueue<Range> pq = new PriorityQueue<>();
    static PriorityQueue<Integer> status = new PriorityQueue<>();
    static List<Integer> temp = new ArrayList<>();
    static int answer = 0;
    
    static class Range implements Comparable<Range> {
        int start, end;
        
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Range o) {
            if(this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }
    
    public int solution(String[][] book_time) {
        N = book_time.length;
        
        for(String[] time : book_time) {
            String[] startTime = time[0].split(":");
            String[] endTime = time[1].split(":");
            int start = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);
            int end = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
            pq.add(new Range(start, end));
        }
        
        setList();
        
        return status.size();
    }
    
    static void setList() {
        while(!pq.isEmpty()) {
            Range range = pq.poll();
            push(range);
        }
    }
    
    static void push(Range range) {
        while(!status.isEmpty()) {
            int now = status.poll();
            if(now > range.start) {
                temp.add(now);
            } else {
                status.add(range.end + 10);
                status.addAll(temp);
                temp.clear();
                return;
            }
        }
        // 넣지 못했다면
        status.add(range.end + 10);
        status.addAll(temp);
        temp.clear();
    }
}
