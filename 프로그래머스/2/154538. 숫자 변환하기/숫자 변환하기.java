import java.util.*;

class Solution {
    static int x, y, n;
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    
    static class Number implements Comparable<Number>{
        int num, count;
        
        public Number(int num, int count) {
            this.num = num;
            this.count = count;
        }
        
        @Override
        public int compareTo(Number number) {
            return Integer.compare(this.count, number.count);
        }
    }
    
    public int solution(int x, int y, int n) {
        this.x = x;
        this.y = y;
        this.n = n;
        visited = new boolean[y + 1];
        
        getCount();
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    static void getCount() {
        PriorityQueue<Number> pq = new PriorityQueue<>();
        pq.add(new Number(x, 0));
        
        
        while(!pq.isEmpty()) {
            Number now = pq.poll();
            if(now.num > y) {
                continue;
            } else if(now.num == y) {
                answer = Math.min(answer, now.count);
                return;
            }
            
            if(visited[now.num]) {
                continue;
            }
            
            visited[now.num] = true;
            pq.add(new Number(now.num + n, now.count + 1));
            pq.add(new Number(now.num * 2, now.count + 1));
            pq.add(new Number(now.num * 3, now.count + 1));
        }
    }
}
