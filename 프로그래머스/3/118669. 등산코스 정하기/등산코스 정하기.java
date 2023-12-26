import java.util.*;

class Solution {
    static class Edge {
        int node, cost, max;

        public Edge(int node, int cost, int max) {
            this.node = node;
            this.cost = cost;
            this.max = max;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        ArrayList<Edge>[] listArr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            listArr[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            listArr[path[0]].add(new Edge(path[1], path[2], path[2]));
            listArr[path[1]].add(new Edge(path[0], path[2], path[2]));
        }

        int[] topAndIntensity = new int[2];
        int top = Integer.MAX_VALUE, intensity = Integer.MAX_VALUE;

        Set<Integer> summitSet = new HashSet<>();
        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int from : gates) {
            int[] minIntensity = new int[n + 1];
            Arrays.fill(minIntensity, Integer.MAX_VALUE);

            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
            pq.addAll(listArr[from]);

            while (!pq.isEmpty()) {
                Edge now = pq.poll();

                if (now.max > intensity) {
                    break;
                }

                if (summitSet.contains(now.node)) {
                    if (now.max < intensity) {
                        intensity = now.max;
                        top = now.node;
                    } else if (now.max == intensity) {
                        top = Math.min(top, now.node);
                    }
                    continue;
                }

                if (minIntensity[now.node] <= now.max) {
                    continue;
                }

                minIntensity[now.node] = now.max;

                for (Edge next : listArr[now.node]) {
                    if (next.max <= intensity) {
                        pq.add(new Edge(next.node, next.cost, Math.max(now.max, next.cost)));
                    }
                }
            }
        }

        topAndIntensity[0] = top;
        topAndIntensity[1] = intensity;
        return topAndIntensity;
    }
}
