import java.util.*;
import java.io.*;

class Solution {
    static String begin, target;
    static String[] words;
    static boolean[] visit;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] arr) {
        this.target = target;
        words = arr;
        visit = new boolean[words.length];
        
        boolean checkFlag = false;
        for(String word : words) {
            if(word.equals(target)) {
                checkFlag = true;
                break;
            }
        }
        
        if(!checkFlag) {
            return 0;
        }
        
        getMin(begin, 0);
        
        return answer;
    }
    
    static void getMin(String word, int count) {
        if(word.equals(target)) {
            answer = Math.min(answer, count);
            return;
        }
        
        for(int i = 0; i < words.length; i++) {
            if(!visit[i] && isPossible(word, words[i])) {
                visit[i] = true;
                getMin(words[i], count + 1);
                visit[i] = false;
            }
        }
    }
    
    static boolean isPossible(String wordA, String wordB) {
        int count = 0;
        for(int i = 0; i < wordA.length(); i++) {
            if(wordA.charAt(i) != wordB.charAt(i)) {
                count++;
            }
            if(count > 1) {
                return false;
            }
        }
        return count == 1;
    }
}
