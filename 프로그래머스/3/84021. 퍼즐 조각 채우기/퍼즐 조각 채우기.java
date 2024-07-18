import java.util.*;

class Solution {
    static int[][] board, table;
    static boolean[][] visited;
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    
    static class Piece implements Comparable<Piece> {
        List<Point> points;
        
        Piece(List<Point> points) {
            this.points = points;
        }
        
        void normalize() {
            Collections.sort(points);
            int minX = points.get(0).x;
            int minY = points.get(0).y;
            for (Point p : points) {
                p.x -= minX;
                p.y -= minY;
            }
            Collections.sort(points);
        }
        
        Piece rotate() {
            List<Point> rotatedPoints = new ArrayList<>();
            for (Point p : points) {
                rotatedPoints.add(new Point(N - 1 - p.y, p.x));
            }
            return new Piece(rotatedPoints);
        }
        
        @Override
        public int compareTo(Piece other) {
            if (this.points.size() != other.points.size()) {
                return this.points.size() - other.points.size();
            }
            for (int i = 0; i < this.points.size(); i++) {
                Point p1 = this.points.get(i);
                Point p2 = other.points.get(i);
                if (p1.x != p2.x) {
                    return p1.x - p2.x;
                }
                if (p1.y != p2.y) {
                    return p1.y - p2.y;
                }
            }
            return 0;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Piece)) {
                return false;
            }
            Piece other = (Piece) obj;
            return this.compareTo(other) == 0;
        }
    }
    
    static class Point implements Comparable<Point> {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compareTo(Point other) {
            if (this.x != other.x) {
                return this.x - other.x;
            }
            return this.y - other.y;
        }
    }
    
    public int solution(int[][] game_board, int[][] table) {
        this.board = game_board;
        this.table = table;
        this.N = board.length;
        
        List<Piece> emptySpaces = findPieces(board, 0);
        List<Piece> tablePieces = findPieces(table, 1);
        
        int totalFilled = 0;
        
        for (Piece emptySpace : emptySpaces) {
            emptySpace.normalize();
            boolean found = false;
            for (int i = 0; i < tablePieces.size() && !found; i++) {
                Piece tablePiece = tablePieces.get(i);
                for (int r = 0; r < 4 && !found; r++) {
                    tablePiece.normalize();
                    if (emptySpace.equals(tablePiece)) {
                        totalFilled += emptySpace.points.size();
                        tablePieces.remove(i);
                        found = true;
                    } else {
                        tablePiece = tablePiece.rotate();
                    }
                }
            }
        }
        
        return totalFilled;
    }
    
    List<Piece> findPieces(int[][] grid, int target) {
        visited = new boolean[N][N];
        List<Piece> pieces = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && grid[i][j] == target) {
                    pieces.add(bfs(i, j, grid, target));
                }
            }
        }
        
        return pieces;
    }
    
    Piece bfs(int x, int y, int[][] grid, int target) {
        List<Point> points = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            points.add(p);
            for (int d = 0; d < 4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];
                if (isInRange(nx, ny) && !visited[nx][ny] && grid[nx][ny] == target) {
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        
        return new Piece(points);
    }
    
    boolean isInRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
