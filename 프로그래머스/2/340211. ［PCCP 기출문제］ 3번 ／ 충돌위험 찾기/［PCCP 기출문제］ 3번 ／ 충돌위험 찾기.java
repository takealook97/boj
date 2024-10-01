import java.util.*;

class Solution {
    static int N, M, D;
    static int[][] pointCoords;
    static int maxTime = 0, answer = 0;
    static int[][] routes;
    static final int Y = 0, X = 1;

    public int solution(int[][] points, int[][] routesInput) {
        N = points.length;
        M = routesInput.length;
        D = routesInput[0].length;
        pointCoords = new int[N + 1][2];

        for (int i = 0; i < N; i++) {
            pointCoords[i + 1][Y] = points[i][Y];
            pointCoords[i + 1][X] = points[i][X];
        }

        routes = routesInput;

        int[] robotTimes = new int[M];
        for (int i = 0; i < M; i++) {
            robotTimes[i] = calculateTotalTime(routes[i]);
            maxTime = Math.max(maxTime, robotTimes[i]);
        }

        countCrash(robotTimes);

        return answer;
    }

    static int calculateTotalTime(int[] route) {
        int time = 0;
        for (int i = 0; i < route.length - 1; i++) {
            int[] from = pointCoords[route[i]];
            int[] to = pointCoords[route[i + 1]];

            time += Math.abs(from[Y] - to[Y]) + Math.abs(from[X] - to[X]);
        }
        return time;
    }

    static void countCrash(int[] robotTimes) {
        Map<String, List<Integer>> positionMap = new HashMap<>();

        int[][] robotPositions = new int[M][2];
        int[] robotRouteIndex = new int[M];
        int[] robotRemainingTime = new int[M];
        boolean[] robotFinished = new boolean[M];

        for (int i = 0; i < M; i++) {
            int startPoint = routes[i][0];
            robotPositions[i][Y] = pointCoords[startPoint][Y];
            robotPositions[i][X] = pointCoords[startPoint][X];
            robotRouteIndex[i] = 0;
            robotRemainingTime[i] = 0;
        }

        for (int time = 0; time <= maxTime; time++) {
            positionMap.clear();

            for (int i = 0; i < M; i++) {
                if (robotFinished[i]) continue;

                int[] currentPosition = robotPositions[i];
                String posKey = currentPosition[Y] + "," + currentPosition[X];
                positionMap.computeIfAbsent(posKey, k -> new ArrayList<>()).add(i);
            }
            
            for (List<Integer> robotsAtPos : positionMap.values()) {
                if (robotsAtPos.size() >= 2) {
                    answer++;
                }
            }

            for (int i = 0; i < M; i++) {
                if (robotFinished[i]) continue;

                if (robotRemainingTime[i] == 0) {
                    if (robotRouteIndex[i] >= routes[i].length - 1) {
                        robotFinished[i] = true;
                        continue;
                    }

                    int[] from = pointCoords[routes[i][robotRouteIndex[i]]];
                    int[] to = pointCoords[routes[i][robotRouteIndex[i] + 1]];

                    robotRemainingTime[i] = Math.abs(from[Y] - to[Y]) + Math.abs(from[X] - to[X]);

                    robotRouteIndex[i]++;
                }

                int[] currentPosition = robotPositions[i];
                int[] targetPosition = pointCoords[routes[i][robotRouteIndex[i]]];

                if (currentPosition[Y] != targetPosition[Y]) {
                    currentPosition[Y] += (currentPosition[Y] < targetPosition[Y]) ? 1 : -1;
                }
                else if (currentPosition[X] != targetPosition[X]) {
                    currentPosition[X] += (currentPosition[X] < targetPosition[X]) ? 1 : -1;
                }

                robotRemainingTime[i]--;
            }
        }
    }
}
