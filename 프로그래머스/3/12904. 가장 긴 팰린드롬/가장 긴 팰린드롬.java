import java.util.*;

class Solution {
    public int solution(String s) {
        char[] arr = s.toCharArray();
        int N = arr.length;
        for(int i = N; i > 0; i--) {
            out:
            for(int j = 0; i + j <= N; j++) {
                for(int k = 0; k < i / 2; k++) {
                    if(arr[j + k] != arr[i + j - k - 1]) {
                        continue out;
                    }
                }
                return i;
            }
        }
        return 1;
    }
}
