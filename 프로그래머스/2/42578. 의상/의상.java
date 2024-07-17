import java.util.*;

class Solution {
    static Map<String, Set<String>> map = new HashMap<>();
    static int answer = 1;
    
    public int solution(String[][] clothes) {
        for(String[] cloth : clothes) {
            if(!map.containsKey(cloth[1])) {
                map.put(cloth[1], new HashSet<>());
            }
            map.get(cloth[1]).add(cloth[0]);
        }
        
        for(Set<String> set : map.values()) {
            answer *= (set.size() + 1);
        }
        
        return answer - 1;
    }
}