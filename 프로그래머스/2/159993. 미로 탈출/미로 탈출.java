import java.util.*;

class Solution {
    static int N, M;
    static char[][] board;
    static boolean[][] visitSrc;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static Queue<Point> queue = new ArrayDeque<>();
    static Point start, exit, lever;
    static int answer = 0;
    
    static class Point {
        int y, x, count;
        
        public Point(int y, int x, int count) {
            this.y = y;
            this.x = x;
            this.count = count;
        }
        
        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;
            return this.y == point.y && this.x == point.x;
        }
    }
    
    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        board = new char[N][M];
        visitSrc = new boolean[N][M];
        visit = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                board[i][j] = maps[i].charAt(j);
                if(board[i][j] == 'S') {
                    start = new Point(i, j, 0);
                } else if(board[i][j] == 'E') {
                    exit = new Point(i, j, 0);
                } else if(board[i][j] == 'L') {
                    lever = new Point(i, j, 0);
                } else if(board[i][j] == 'X') {
                    visitSrc[i][j] = true;
                }
            }
        }
        
        if(!getMin(false)) {
            return -1;
        }
        if(!getMin(true)) {
            return -1;
        }
        
        return answer;
    }
    
    static void resetVisit() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visit[i][j] = visitSrc[i][j];
            }
        }
    }
    
    static boolean getMin(boolean isLever) {
        queue.clear();
        resetVisit();
        if(!isLever) {
            queue.add(start);
            visit[start.y][start.x] = true;
        } else {
            queue.add(lever);
            visit[lever.y][lever.x] = true;
        }
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if(!isLever) {
                if(now.equals(lever)) {
                    answer += now.count;
                    return true;
                }
            } else {
                if(now.equals(exit)) {
                    answer += now.count;
                    return true;
                }
            }
            
            for(int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if(isPossible(nextY, nextX)) {
                    visit[nextY][nextX] = true;
                    queue.add(new Point(nextY, nextX, now.count + 1));
                }
            }
        }
        return false;
    }
    
    static boolean isPossible(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M && !visit[y][x];
    }
}
