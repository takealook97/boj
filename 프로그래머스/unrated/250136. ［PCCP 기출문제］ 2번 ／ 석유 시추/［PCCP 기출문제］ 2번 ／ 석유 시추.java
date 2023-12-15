import java.io.*;
import java.util.*;


class Solution {
    static int n, m, idx = 2, answer = 0;
    static int[][] board;
    static boolean[][] visit;
    static List<Point> oilList;
    static List<Integer> keyList = new ArrayList<>();
    static Map<Integer, List<Point>> oilMap = new HashMap<>();
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    
    static final int EMPTY = 0, OIL = 1;
    
    static class Point {
        int y, x;
        
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        board = land;
        visit = new boolean[n][m];
        
        // set oil
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(board[i][j] == 1 && !visit[i][j]) {
                    updateBoard(i, j);
                }
            }
        }
        
        for(int i = 0; i < m; i++) {
            answer = Math.max(answer, getOilSum(i));
        }
        
        return answer;
    }
    
    static int getOilSum(int x) {
        keyList.clear();
        int sum = 0;
        int y = -1;
        while(++y < n) {
            if(board[y][x] == EMPTY) {
                continue;
            }
            
            if(!keyList.contains(board[y][x])) {
                keyList.add(board[y][x]);
                sum += oilMap.get(board[y][x]).size();
            }
        }
        
        return sum;
    }
    
    static void updateBoard(int y, int x) {
        oilList = new ArrayList<>();
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(y, x));
        visit[y][x] = true;
        oilList.add(new Point(y, x));
        
        int sum = 1;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for(int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if(isPossible(nextY, nextX)) {
                    visit[nextY][nextX] = true;
                    sum++;
                    oilList.add(new Point(nextY, nextX));
                    queue.add(new Point(nextY, nextX));
                }
            }
        }
        
        // set idx on board
        for(Point point : oilList) {
            board[point.y][point.x] = idx;
        }
        
        // save point on map
        oilMap.put(idx++, oilList);
    }
    
    static boolean isPossible(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m && board[y][x] == OIL && !visit[y][x];
    }
    
    static void resetVisit() {
        for(int i = 0; i < n; i++) {
            Arrays.fill(visit[i], false);
        }
    }
}
