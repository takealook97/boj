import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[numbers.length];
        
        stack.add(0);
        for(int i = 1; i < N; i++) {
            while(!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            stack.add(i);
        }
        
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }
        
        return answer;
    }
}
