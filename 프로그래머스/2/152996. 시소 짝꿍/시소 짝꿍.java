import java.util.*;

class Solution {
    static double[] ratiosSrc = {1, 2.0 / 3, 0.5, 2, 1.5, 4.0 / 3, 0.75};
    
    public long solution(int[] weights) {
        HashMap<Double, Integer> map = new HashMap<>();
        long answer = 0;
        
        for (int weight : weights) {
            double[] ratios = Arrays.copyOf(ratiosSrc, ratiosSrc.length);
            for (double ratio : ratios) {
                double key = weight * ratio;
                answer += map.getOrDefault(key, 0);
            }
            map.put((double) weight, map.getOrDefault((double) weight, 0) + 1);
        }
        
        return answer;
    }
}
