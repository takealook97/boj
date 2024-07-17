class Solution {
    static int x = 0, y = 0;
    
    public int solution(int[][] sizes) {
        int min = 0, max = 0;
        for(int[] size : sizes) {
            min = Math.min(size[0], size[1]);
            max = Math.max(size[0], size[1]);
            x = Math.max(x, min);
            y = Math.max(y, max);
        }
        
        return x * y;
    }
}
