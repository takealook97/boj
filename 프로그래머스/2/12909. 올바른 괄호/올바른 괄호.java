import java.util.*;

class Solution {
    static Queue<Character> queue = new ArrayDeque<>();
    static boolean answer = true;
    
    static final char OPEN = '(', CLOSE = ')';
    
    boolean solution(String s) {
        for (char basket : s.toCharArray()) {
            if(basket == OPEN) {
                queue.add(basket);
            } else {
                if(queue.isEmpty()) {
                    return false;
                } else if(queue.peek() == OPEN) {
                    queue.poll();
                }
            }
        }
        

        return queue.isEmpty();
    }
}