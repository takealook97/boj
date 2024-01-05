import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static ArrayList<Edge>[] listArr;
    static int[] distS, distA, distB, distSum;
    static boolean[] visit;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int answer = Integer.MAX_VALUE;
    
    static final int A = 0, B = 1, START = 2;
    static final int INF = 987654321;
    
    static class Edge implements Comparable<Edge> {
        int node, weight;
        
        public Edge (int node, int weight){
            this.node = node;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        
        visit = new boolean[N + 1];
        
        listArr = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            listArr[i] = new ArrayList<>();
        }
        for(int[] fare : fares) {
            listArr[fare[0]].add(new Edge(fare[1], fare[2]));
            listArr[fare[1]].add(new Edge(fare[0], fare[2]));
        }
        
        setDistance(s, START);
        setDistance(a, A);
        setDistance(b, B);
        getSum();
        
        return answer;
    }
    
    static void setDistance(int start, int type) {
        int[] dist = new int[N + 1];
        pq.clear();
        Arrays.fill(visit, false);
        Arrays.fill(dist, INF);
        
        dist[start] = 0;        
        pq.add(new Edge(start, 0));
        
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
        
        if(type == START) {
            distS = dist;
        } else if(type == A) {
            distA = dist;
        } else if(type == B) {
            distB = dist;
        }
    }
    
    static void getSum() {
        for(int i = 0; i <= N; i++) {
            int sum = distS[i] + distA[i] + distB[i];
            if(0 <= sum && sum < INF) {
                answer = Math.min(answer, sum);
            }
        }
    }
}
