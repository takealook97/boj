import java.util.*;
import java.io.*;

class Solution {
    static int N;
    static int[] board;
    static ArrayList<Integer>[] listArr;
    static boolean[] visit;
    static int maxValue = 0, answer = 0;
    
    static class Edge {
        int node, count;
        
        public Edge(int node, int count) {
            this.node = node;
            this.count = count;
        }
    }
    
    public int solution(int n, int[][] edge) {
        N = n;
        board = new int[N + 1];
        visit = new boolean[N + 1];
        
        // init listArr;
        listArr = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }
        for(int[] arr : edge) {
            listArr[arr[0]].add(arr[1]);
            listArr[arr[1]].add(arr[0]);
        }
        
        getMax();
        
        return answer;
    }
    
    static void getMax() {
        Queue<Edge> queue = new ArrayDeque<>();
        queue.add(new Edge(1, 0));
        visit[1] = true;
        
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            int now = edge.node;
            int nowCount = edge.count;
            
            if(nowCount > maxValue) {
                answer = 1;
                maxValue = nowCount;
            } else if(nowCount == maxValue) {
                answer++;
            }
            
            for(int next : listArr[now]) {
                if(!visit[next]) {
                    visit[next] = true;
                    queue.add(new Edge(next, nowCount + 1));
                }
            }
        }
    }
}
