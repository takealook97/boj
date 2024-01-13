import java.util.*;
import java.io.*;

class Solution {
    static int N;
    static ArrayList<Integer>[] listArr;
    static boolean[] visit;
    static Queue<Integer> queue = new ArrayDeque<>();
    static int answer = 0;

    public int solution(int n, int[][] computers) {
        N = n;
        listArr = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(computers[i][j] == 1) {
                    listArr[i + 1].add(j + 1);
                }
            }
        }
        visit = new boolean[N + 1];
        
        for(int i = 1; i <= N; i++) {
            if(!visit[i]) {
                checkVisited(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    static void checkVisited(int start) {
        queue.clear();
        queue.add(start);
        visit[start] = true;
        
        while(!queue.isEmpty()) {
            int now = queue.poll();
            
            for(int next : listArr[now]) {
                if(!visit[next]) {
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}
