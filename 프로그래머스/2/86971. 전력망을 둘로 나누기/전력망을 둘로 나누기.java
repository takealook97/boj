import java.util.*;

class Solution {
    static int N, linkCount, start;
    static boolean[][] linkSrc;
    static boolean[][] linkCopy;
    static Queue<Integer> queue = new ArrayDeque<>();
    static int answer = Integer.MAX_VALUE;
    
    public int solution(int n, int[][] wires) {
        N = n;
        linkCount = wires.length;
        start = wires[0][0];
        
        linkSrc = new boolean[N + 1][N + 1];
        linkCopy = new boolean[N + 1][N + 1];
        
        for(int i = 1; i <= N; i++) {
            Arrays.fill(linkSrc[i], true);
        }
        
        for(int i = 0; i < linkCount; i++) {
            linkSrc[wires[i][0]][wires[i][1]] = false;
            linkSrc[wires[i][1]][wires[i][0]] = false;
        }
        
        for(int i = 0; i < linkCount; i++) {
            copy();
            linkCopy[wires[i][0]][wires[i][1]] = true;
            linkCopy[wires[i][1]][wires[i][0]] = true;
            int gap = getGap();
            if(gap == 0) {
                return 0;
            }
            answer = Math.min(answer, gap);
        }
        
        return answer;
    }
    
    static void copy() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                linkCopy[i][j] = linkSrc[i][j];
            }
        }
    }
    
    static int getGap() {
        queue.clear();
        int count = 1;
        
        queue.add(start);// 임의의 송전탑 추가
        
        while(!queue.isEmpty()) {
            int from = queue.poll();
            
            for(int to = 1; to <= N; to++) {
                if(!linkCopy[from][to]) {
                    linkCopy[from][to] = true;
                    linkCopy[to][from] = true;
                    count++;
                    queue.add(to);
                }
            }
        }
        
        return Math.abs(N - count * 2);
    }
}
