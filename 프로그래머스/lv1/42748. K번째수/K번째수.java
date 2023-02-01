import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < commands.length; i++) {
            int a = commands[i][0] - 1;
            int b = commands[i][1] - 1;
            int c = commands[i][2] - 1;
            int[] arr = new int[b - a + 1];
            int arrIndex = 0;
            for (int j = a; j <= b; j++) {
                arr[arrIndex++] = array[j];
            }
            Arrays.sort(arr);
            list.add(arr[c]);
        }
        int[] answer = new int[commands.length];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}