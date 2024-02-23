import java.util.*;

class Solution {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int answer = 0;
    
    static class Point implements Comparable<Point> {
        int y, x, count;
        
        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
        
        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.count, o.count);
        }
    }
    
    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        board = maps;
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        
        getMin();
        
        return answer == 0 ? -1 : answer;
    }
    
    static void getMin() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0, 0, 1));
        visited[0][0] = true;
        
        while(!pq.isEmpty()) {
            Point now = pq.poll();
            if(now.y == N - 1 && now.x == M - 1) {
                answer = now.count;
                return;
            }
            
            for(int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if(isPossible(nextY, nextX)) {
                    visited[nextY][nextX] = true;
                    pq.add(new Point(nextY, nextX, now.count + 1));
                }
            }
        }
    }
    
    static boolean isPossible(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M && !visited[y][x];
    }
}
