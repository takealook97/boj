import java.io.*;
import java.util.*;

class Solution {
    static int[] arr;
    
    public int[] solution(int[] sequence, int k) {
        arr = new int[sequence.length];
        arr[0] = sequence[0];
        for(int i = 1; i < sequence.length; i++) {
            arr[i] = arr[i - 1] + sequence[i];
        }
        
        int left = 0;
        int right = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];
        
        while(left <= right && right < arr.length) {
            sum = 0;
            if(left == right) {
                sum = sequence[right];
            } else {
                if(left == 0) {
                    sum = arr[right];
                } else {
                    sum = arr[right] - arr[left - 1];
                }
            }
            
            if(sum < k) {
                right++;
            } else if(sum > k) {
                if(left < right) {
                    left++;
                } else {
                    left++;
                    right++;
                }
            } else {
                if(minLength > right - left + 1) {
                    minLength = right - left + 1;
                    answer = new int[]{left, right};
                }
                left++;
                right++;
            }
            
            
        }
        
        
        return answer;
    }
}