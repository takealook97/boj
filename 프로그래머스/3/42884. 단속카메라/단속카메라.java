import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1;
        Arrays.sort(routes, (o1, o2) -> (o1[1] - o2[1]));
        // System.out.println(Arrays.deepToString(routes));
        int pointer = routes[0][1];
        for(int i = 1; i < routes.length; i++){
            if(routes[i][0] > pointer || pointer > routes[i][1]){
                pointer = routes[i][1];
                answer++;
            }
        }
        return answer;
    }
}
