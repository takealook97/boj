import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[][] board;
    static boolean[][][] visit;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static int answer = Integer.MAX_VALUE;
    
    static class Point {
        int y, x, dir, cost;
        
        public Point(int y, int x, int dir, int cost) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] arr) {
        N = arr.length;
        board = arr;
        visit = new boolean[N][N][4];
        
        getMin();
        
        return answer;
    }
    
    static void getMin() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(0, 0, -1, 0));
        for(int i = 0; i < 4; i++) {
            visit[0][0][i] = true;
        }
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if(now.y == N - 1 && now.x == N - 1) {
                answer = Math.min(answer, now.cost);
                continue;
            }
            
            for(int nextDir = 0; nextDir < 4; nextDir++) {
                int nextY = now.y + dy[nextDir];
                int nextX = now.x + dx[nextDir];
                int nextCost = now.cost + 100;
                if(now.dir != nextDir && now.dir != -1) {
                    nextCost += 500;
                }
                if(isPossible(nextY, nextX) &&
                   (!visit[nextY][nextX][nextDir] || board[nextY][nextX] >= nextCost)) {
                    visit[nextY][nextX][nextDir] = true;
                    board[nextY][nextX] = nextCost;
                    queue.add(new Point(nextY, nextX, nextDir, nextCost));
                }
            }
        }
    }
    
    static boolean isPossible(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N && board[y][x] != 1;
    }
}
