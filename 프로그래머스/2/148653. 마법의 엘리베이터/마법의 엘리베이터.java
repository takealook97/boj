import java.util.*;

class Solution {
    static int N;
    static int[] arr;
    static int answer = 0;
    
    public int solution(int storey) {
        String number = String.valueOf(storey);
        N = number.length();
        arr = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = number.charAt(i) - '0';
        }
        
        for(int i = N - 1; i >= 0; i--) {
            if(arr[i] < 5) {
                answer += arr[i];
                arr[i] = 0;
            } else if(arr[i] > 5) {
                answer += (10 - arr[i]);
                arr[i] = 10;
                setUpper(i);
            } else {
                if(i > 0 && arr[i - 1] >= 5) {
                    answer += (10 - arr[i]);
                    arr[i] = 10;
                    setUpper(i);
                } else {
                    answer += arr[i];
                    arr[i] = 0;
                }
            }
        }
        
        return answer;
    }
    
    static void setUpper(int point) {
        for(int i = point; i >= 0; i--) {
            if(arr[i] >= 10) {
                arr[i] -= 10;
                if(i == 0) {
                    answer++;
                    return;
                }
                arr[i - 1]++;
            }
        }
    }
}
