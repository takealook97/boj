import java.util.*;

class Solution {
    static int N;
    static ArrayList<Edge>[] listArr;
    static boolean[] visit;
    static int[] dist, answer;
    static final int INF = 987654321;
    
    static class Edge implements Comparable<Edge> {
        int node, weight;
        
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        N = n;
        
        listArr = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }
        for(int[] road : roads) {
            listArr[road[0]].add(new Edge(road[1], 1));
            listArr[road[1]].add(new Edge(road[0], 1));
        }
        
        setDist(destination);
        
        setAnswer(sources);
        
        return answer;
    }
    
    static void setDist(int dest) {
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        visit = new boolean[N + 1];
        pq.add(new Edge(dest, 0));
        dist[dest] = 0;
        
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(!visit[now.node]) {
                visit[now.node] = true;
                for(Edge next : listArr[now.node]) {
                    if(dist[next.node] > now.weight + next.weight) {
                        dist[next.node] = now.weight + next.weight;
                        pq.add(new Edge(next.node, dist[next.node]));
                    }
                }
            }
        }
    }
    
    static void setAnswer(int[] sources) {
        answer = new int[sources.length];
        int idx = 0;
        for(int i : sources) {
            answer[idx++] = dist[i] < INF ? dist[i] : -1;
        }
    }
}
