import java.util.*;

class Solution {
    static final char NONE1 = '.', NONE2 = ',';
    
    public int solution(String[] words) {
        int N = words.length;
        int answer = 0;
        Arrays.sort(words);
        
        String prev, now, next;
        int len1, len2, len3;
        char c1, c2, c3;
        boolean check1, check2;
        
        o :
        for (int i = 0; i < N; i++) {
            prev = i > 0 ? words[i - 1] : "";
            now = words[i];
            next = i < N - 1 ? words[i + 1] : "";
            
            len1 = prev.length();
            len2 = now.length();
            len3 = next.length();
            
            
            check1 = false;
            check2 = false;
            for(int j = 0; j < len2; j++) {
                c1 = len1 > j ? prev.charAt(j) : NONE1;
                c2 = now.charAt(j);
                c3 = len3 > j ? next.charAt(j) : NONE2;
                
                if(c1 != c2 && c2 != c3) {
                    answer += j + 1;
                    continue o;
                } else if(c1 != c2) {
                    check1 = true;
                    if(check2) {
                        answer += j + 1;
                        continue o;
                    }
                } else if(c2 != c3) {
                    check2 = true;
                    if(check1) {
                        answer += j + 1;
                        continue o;
                    }
                }
            }
            
            answer += len2;
        }
        
        return answer;
    }
}
