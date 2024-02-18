import java.util.*;

class Solution {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static Queue<Point> queue = new ArrayDeque<>();
    static ArrayList<Integer> answerList = new ArrayList<>();
    
    static class Point {
        int y, x;
        
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        board = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            String line = maps[i];
            for(int j = 0; j < M; j++) {
                char pos = line.charAt(j);
                if(pos == 'X') {
                    board[i][j] = -1;
                    visited[i][j] = true;
                } else {
                    board[i][j] = pos - '0';
                }
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j]) {
                    answerList.add(getDayCount(new Point(i, j)));
                }
            }
        }
        
        if(answerList.isEmpty()) {
            int[] answerArr = {-1};
            return answerArr;
        }
        
        Collections.sort(answerList);
        int[] answerArr = new int[answerList.size()];
        for(int i = 0; i < answerArr.length; i++) {
            answerArr[i] = answerList.get(i);
        }
        return answerArr;
    }
    
    static int getDayCount(Point start) {
        queue.clear();
        queue.add(start);
        visited[start.y][start.x] = true;
        int count = board[start.y][start.x];
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if(isPossible(nextY, nextX)) {
                    visited[nextY][nextX] = true;
                    count += board[nextY][nextX];
                    queue.add(new Point(nextY, nextX));
                }
            }
        }
        
        return count;
    }
    
    static boolean isPossible(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M && !visited[y][x];
    }
}
