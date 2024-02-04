import java.util.*;

class Solution {
    static int N;
    static ArrayList<Integer>[] listArr;
    static int answer = 0;
    
    public int solution(int n, int[][] lighthouse) {
        N = n;
        listArr = new ArrayList[N + 1];
        
        for(int i = 0; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }
        
        for(int[] arr : lighthouse) {
            listArr[arr[0]].add(arr[1]);
            listArr[arr[1]].add(arr[0]);
        }
        
        getMin(0, 1);
        
        return answer;
    }
    
    static int getMin(int prev, int now) {
        if(listArr[now].size() == 1 && listArr[now].get(0) == prev) {
            return 1;
        }
        
        int temp = 0;
        for (int next : listArr[now]) {
            if (next != prev) {
                temp += getMin(now, next);
            }
        }
        
		if (temp == 0)  {
        	return 1;
        }
        
        answer++;
        return 0;
    }
}
