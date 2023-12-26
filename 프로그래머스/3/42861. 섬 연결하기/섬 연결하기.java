import java.util.*;
import java.io.*;

class Solution {
    static ArrayList<Edge>[] listArr;
    static boolean[] visit;
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
    static int answer = 0;
    
    static class Edge implements Comparable<Edge> {
        int node, cost;
        
        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    
    public int solution(int n, int[][] costs) {
        listArr = new ArrayList[n];
        visit = new boolean[n];
        for(int i = 0; i < n; i++) {
            listArr[i] = new ArrayList<>();
        }
        
        for(int[] arr : costs) {
            listArr[arr[0]].add(new Edge(arr[1], arr[2]));
            listArr[arr[1]].add(new Edge(arr[0], arr[2]));
        }
        
        pq.add(new Edge(0, 0));
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (!visit[now.node]) {
				visit[now.node] = true;
				answer += now.cost;
				if (!listArr[now.node].isEmpty()) {
					for (Edge next : listArr[now.node]) {
						if (!visit[next.node]) {
							pq.add(next);
						}
					}
				}
			}
		}
        
        return answer;
    }
}
