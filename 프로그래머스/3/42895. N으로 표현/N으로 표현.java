import java.util.*;

class Solution {
    static int N, number;
    static Map<Integer, Set<Integer>> map = new HashMap<>();
    
    public int solution(int N, int number) {
        if(N == number) {
            return 1;
        }
        
        for(int i = 1; i <= 8; i++) {
            map.put(i, new HashSet<>());
        }
        
        map.get(1).add(N);
        
        for(int i = 2; i <= 8; i++) {
            Set<Integer> set = map.get(i);
            for(int j = 1; j < i; j++) {
                Set<Integer> setA = map.get(j);
                Set<Integer> setB = map.get(i - j);
                
                for(int a : setA) {
                    for(int b : setB) {
                        set.add(a + b);
                        set.add(a - b);
                        set.add(a * b);
                        if(b != 0) {
                            set.add(a / b);
                        }
                    }
                }
                
                set.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            }
            
            if(set.contains(number)) {
				return i;
			}
        }
        
        return -1;
    }
}
