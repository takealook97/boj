import java.util.*;

class Solution {
    static Map<Integer, Integer> map = new HashMap<>();
    static PriorityQueue<Fruit> pq = new PriorityQueue<>();
    static int answer = 0;
    
    static class Fruit implements Comparable<Fruit> {
        int size, count;
        
        public Fruit(int size, int count) {
            this.size = size;
            this.count = count;
        }
        
        @Override
        public int compareTo(Fruit o) {
            if(this.count == o.count) {
                return Integer.compare(this.size, o.size);
            }
            
            return Integer.compare(this.count, o.count);
        }
    }
    
    public int solution(int k, int[] tangerine) {
        for(int num : tangerine) {
            if(map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        
        for(int size : map.keySet()) {
            pq.add(new Fruit(size, map.get(size)));
        }
        
        int target = tangerine.length - k;
        while(!pq.isEmpty() && target >= 0) {
            int nextcount = pq.poll().count;
            target -= nextcount;
            if(target >= 0) {
                continue;
            }
            
        }
        
        return pq.size() + 1;
    }
}