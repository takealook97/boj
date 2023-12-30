import java.util.*;

class Solution {
    static int N, M, destY, destX;
    static char[][] board;
    static HashSet<Point> set = new HashSet<>();
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static int answer = Integer.MAX_VALUE;
    
    static final char POSSIBLE = '.', IMPOSSIBLE = 'D', FROM = 'R', TO = 'G';
    
	static class Point {
		int y, x, dir, count;

		public Point(int y, int x, int dir, int count) {
			this.y = y;
			this.x = x;
			this.dir = dir;
            this.count = count;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			Point point = (Point)o;

			if (y != point.y)
				return false;
			if (x != point.x)
				return false;
			return dir == point.dir;
		}

		@Override
		public int hashCode() {
			int result = y;
			result = 31 * result + x;
			result = 31 * result + dir;
			return result;
		}
    }
        
    public int solution(String[] arr) {
        N = arr.length;
        M = arr[0].length();
        board = new char[N][M];
        int startY = 0, startX = 0;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                board[i][j] = arr[i].charAt(j);
                if(board[i][j] == FROM) {
                    startY = i;
                    startX = j;
                } else if(board[i][j] == TO) {
                    destY = i;
                    destX = j;
                }
            }
        }
        
        return getMin(new Point(startY, startX, -1, 0));
    }
    
    static int getMin(Point start) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            if(now.y == destY && now.x == destX) {
                return now.count;
            }
            
            for(int i = 0; i < 4; i++) {
                int nextY = now.y;
                int nextX = now.x;
            
                while(true) {
                    if(isMovable(nextY + dy[i], nextX + dx[i])) {
                        nextY += dy[i];
                        nextX += dx[i];
                    } else break;
                }
                
                if(nextY == now.y && nextX == now.x) {
                    continue;
                }
                
                Point next = new Point(nextY, nextX, i, now.count + 1);
                if(set.add(next)) {
                    queue.add(next);
                }
            }
        }
        
        return -1;
    }
    
    static boolean isMovable(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M && board[y][x] != IMPOSSIBLE;
    }
}
