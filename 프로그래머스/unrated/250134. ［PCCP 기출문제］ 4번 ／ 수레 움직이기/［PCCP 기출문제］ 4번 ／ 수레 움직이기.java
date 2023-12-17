import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static int[][] board;
    static boolean[][] visitR, visitB;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static Point destR, destB;
    static int answer = Integer.MAX_VALUE;
    
    static final int FROM_R = 1, TO_R = 3, FROM_B = 2, TO_B = 4, EMPTY = 0, WALL = 5;
    static final int IMPOSSIBLE = 0;
    
    static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}

		@Override
		public boolean equals(Object o) {
			Point point = (Point) o;
            return this.y == point.y && this.x == point.x;
		}
	}
    
    static class Cart {
        Point from, to;
        
        public Cart(Point from, Point to) {
            this.from = from;
            this.to = to;
        }
    }
    
    public int solution(int[][] maze) {
        N = maze.length;
        M = maze[0].length;
        board = maze;
        visitR = new boolean[N][M];
        visitB = new boolean[N][M];
        
        Point red = null;
        Point blue = null;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i][j] == FROM_R) {
                    red = new Point(i, j);
                    visitR[i][j] = true;
                    board[i][j] = EMPTY;
                } else if(board[i][j] == FROM_B) {
                    blue = new Point(i, j);
                    visitB[i][j] = true;
                    board[i][j] = EMPTY;
                } else if(board[i][j] == TO_R) {
                    destR = new Point(i, j);
                } else if(board[i][j] == TO_B) {
                    destB = new Point(i, j);
                }
            }
        }
        
        getMinCount(red, blue, 0);
        
        if(answer == Integer.MAX_VALUE) {
            return IMPOSSIBLE;
        }      
        return answer;
    }
    
    static void getMinCount(Point red, Point blue, int count) {
        if(red.equals(destR) && blue.equals(destB)) {
            answer = Math.min(answer, count);
        } else if(!red.equals(destR) && blue.equals(destB)) {// blue만 도착
            for(int i = 0; i < 4; i++) {
                int nextY = red.y + dy[i];
                int nextX = red.x + dx[i];
                
                if(!isInRange(nextY, nextX)) {
                    continue;
                }
                
                if(isMovable(nextY, nextX, true, true)) {
                    visitR[nextY][nextX] = true;
                    getMinCount(new Point(nextY, nextX), blue, count + 1);
                    visitR[nextY][nextX] = false;
                }
            }
        } else if(red.equals(destR) && !blue.equals(destB)) {// red만 도착
            for(int i = 0; i < 4; i++) {
                int nextY = blue.y + dy[i];
                int nextX = blue.x + dx[i];
                
                if(!isInRange(nextY, nextX)) {
                    continue;
                }
                
                if(isMovable(nextY, nextX, false, true)) {
                    visitB[nextY][nextX] = true;
                    getMinCount(red, new Point(nextY, nextX), count + 1);
                    visitB[nextY][nextX] = false;
                }
            }
        } else if(!red.equals(destR) && !blue.equals(destB)) {// 둘다 도착지점 아닐 경우
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    int nextRY = red.y + dy[i];
                    int nextRX = red.x + dx[i];
                    int nextBY = blue.y + dy[j];
                    int nextBX = blue.x + dx[j];
                    
                    if(!isInRange(nextRY, nextRX) || !isInRange(nextBY, nextBX)) {
                        continue;
                    }
                    
                    Point nextRed = new Point(nextRY, nextRX);
                    Point nextBlue = new Point(nextBY, nextBX);
                    if(isPossible(new Cart(red, nextRed), new Cart(blue, nextBlue))) {
                        visitR[nextRY][nextRX] = true;
                        visitB[nextBY][nextBX] = true;
                        getMinCount(nextRed, nextBlue, count + 1);
                        visitR[nextRY][nextRX] = false;
                        visitB[nextBY][nextBX] = false;
                    }
                }
            }
        }
    }
    
    static boolean isPossible(Cart r, Cart b) {// red blue 둘다 체크
        boolean check = isMovable(r.to.y, r.to.x, true, false) && isMovable(b.to.y, b.to.x, false, false) && // 범위 확인 및 칸 확인
            !visitR[r.to.y][r.to.x] && !visitB[b.to.y][b.to.x] && // 방문 확인
            !r.to.equals(b.to); // 목적지 다른 칸인지 확인
        if(!check) {
            return false;
        }
        if(r.to.equals(b.from) && r.from.equals(b.to)) { // 자리 변경여부 확인
            return false;
        }
        
        return true;
    }
    
    static boolean isMovable(int y, int x, boolean isRed, boolean check) {
        if(board[y][x] == WALL) {
            return false;
        }
        
        if(!check) {// 둘다 도착 안한 경우 침범 가능
            if(isRed) {
                return (board[y][x] == EMPTY || board[y][x] == TO_R || board[y][x] == TO_B) && !visitR[y][x];
            }
            return (board[y][x] == EMPTY || board[y][x] == TO_R || board[y][x] == TO_B) && !visitB[y][x];
        }
        
        // 다른 하나 도착한 상태일 경우 침범 불가
        if(isRed) {
            return (board[y][x] == EMPTY || board[y][x] == TO_R) && !visitR[y][x];
        }
        return (board[y][x] == EMPTY || board[y][x] == TO_B) && !visitB[y][x];
    }
    
    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}