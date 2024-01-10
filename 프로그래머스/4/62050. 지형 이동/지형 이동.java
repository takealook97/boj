import java.util.*;
import java.io.*;

class Solution {
    static int N, height, idx = 0;
    static int[][] board;
    static int[][] group;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
    static ArrayList<Integer>[] adjArr;
    static ArrayList<Edge>[] edgeArr;
    static boolean[] idxVisit;
    static int answer = 0;
    
    static class Point {
        int y, x, cost;
        
        public Point (int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }
    
    static class Edge {
        int node, gap;
        
        public Edge (int node, int gap) {
            this.node = node;
            this.gap = gap;
        }
        
        @Override
        public String toString() {
            return "node: " + node + " | gap: " + gap; 
        }
    }
    
    public int solution(int[][] land, int h) {
        N = land.length;
        board = land;
        group = new int[N][N];
        visit = new boolean[N][N];
        height = h;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(!visit[i][j]) {
                    ++idx;
                    setGroup(i, j);
                }
            }
        }
        
        // reset visit;
        for(int i = 0; i < N; i++) {
            Arrays.fill(visit[i], false);
        }
        
        // set adjacent array
        adjArr = new ArrayList[idx + 1];
        for(int i = 0; i <= idx; i++) {
            adjArr[i] = new ArrayList<>();
        }
        
        edgeArr = new ArrayList[idx + 1];
        for(int i = 1; i <= idx; i++) {
            edgeArr[i] = new ArrayList<>();
        }
        
        
        setAdjArr();
        setEdgeArr();
        getMin();
        
        return answer;
    }
    
    static void setGroup(int startY, int startX) { // without height
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startY, startX, 0));
        group[startY][startX] = idx;
        visit[startY][startX] = true;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            for(int i = 0; i < 4; i++) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                if(isInRange(nextY, nextX) && !visit[nextY][nextX] &&
                   Math.abs(board[now.y][now.x] - board[nextY][nextX]) <= height) {
                    group[nextY][nextX] = idx;
                    visit[nextY][nextX] = true;
                    queue.add(new Point(nextY, nextX, now.cost));
                }
            }
        }
    }
    
    static void setAdjArr() {
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                int now = group[y][x];
                for(int i = 0; i < 4; i++) {
                    int nextY = y + dy[i];
                    int nextX = x + dx[i];
                    if(isInRange(nextY, nextX)) {
                        int next = group[nextY][nextX];
                        if(!adjArr[now].contains(next) && now != next) {
                            adjArr[now].add(next);
                            edgeArr[now].add(new Edge(next, Integer.MAX_VALUE));
                        }
                    }
                }
            }
        }
    }
    
    static void setEdgeArr() {
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                int now = group[y][x];
                for(int i = 0; i < 4; i++) {
                    int nextY = y + dy[i];
                    int nextX = x + dx[i];
                    if(isInRange(nextY, nextX)) {
                        int next = group[nextY][nextX];
                        if(now == next) {
                            continue;
                        }
                        
                        Edge targetEdge = null;
                        for(Edge edge : edgeArr[now]) {
                            if(edge.node == next) {
                                targetEdge = edge;
                                break;
                            }
                        }
                        
                        if(targetEdge != null) {
                            targetEdge.gap = Math.min(targetEdge.gap, Math.abs(board[y][x] - board[nextY][nextX]));
                        }
                    }
                }
            }
        }
    }
    
    static void getMin() {
        idxVisit = new boolean[idx + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.gap - b.gap);
        int sum = 0;

        idxVisit[1] = true;
        for (Edge edge : edgeArr[1]) {
            pq.add(edge);
        }

        while (!pq.isEmpty()) {
            Edge nowEdge = pq.poll();

            if (idxVisit[nowEdge.node]) {
                continue;
            }

            idxVisit[nowEdge.node] = true;
            sum += nowEdge.gap;

            for (Edge nextEdge : edgeArr[nowEdge.node]) {
                if (!idxVisit[nextEdge.node]) {
                    pq.add(nextEdge);
                }
            }
        }

        answer = sum;
    }

    
    static boolean isAllVisited() {
        for(int i = 1; i <= idx; i++) {
            if(!idxVisit[i]) {
                return false;
            }
        }
        return true;
    }
    
    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
