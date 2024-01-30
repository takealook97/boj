import java.util.*;

class Solution {
    static int N;
    static int[] arrA;
    static int[] arrB;
    static long answer = 0;
    
    public long solution(int[] sequence) {
        N = sequence.length;
        if(N == 1) {
            return Math.abs(sequence[0]);
        }
        arrA = new int[N];
        arrB = new int[N];
        
        for(int i = 0; i < N; i++) {
            if(i % 2 == 0) {
                arrA[i] = sequence[i];
                arrB[i] = -sequence[i];
            } else {
                arrA[i] = -sequence[i];
                arrB[i] = sequence[i];
            }
        }
        
        getMax(arrA);
        getMax(arrB);
        
        return answer;
    }
    
    static void getMax(int[] arr) {
        long result = arr[0];
        long max = arr[0];

        for (int i = 1; i < N; i++) {
            max = Math.max(arr[i], max + arr[i]);
            result = Math.max(result, max);
        }

        answer = Math.max(answer, Math.abs(result));
    }
}
