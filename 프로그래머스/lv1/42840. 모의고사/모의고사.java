import java.util.ArrayList;

public class Solution {
    public int[] solution(int[] answers) {
        int[] one = new int[answers.length];
        int[] two = new int[answers.length];
        int[] three = new int[answers.length];
        int scoreOne = 0;
        int scoreTwo = 0;
        int scoreThree = 0;
        for (int i = 0; i < answers.length; i++) {
            if (i % 5 == 0) one[i] = 1;
            else if (i % 5 == 1) one[i] = 2;
            else if (i % 5 == 2) one[i] = 3;
            else if (i % 5 == 3) one[i] = 4;
            else one[i] = 5;

            if (i % 2 == 0) two[i] = 2;
            else if (i % 8 == 1) two[i] = 1;
            else if (i % 8 == 3) two[i] = 3;
            else if (i % 8 == 5) two[i] = 4;
            else two[i] = 5;

            if (i % 10 == 0 || i % 10 == 1) three[i] = 3;
            else if (i % 10 == 2 || i % 10 == 3) three[i] = 1;
            else if (i % 10 == 4 || i % 10 == 5) three[i] = 2;
            else if (i % 10 == 6 || i % 10 == 7) three[i] = 4;
            else three[i] = 5;
        }
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == one[i]) scoreOne++;
            if (answers[i] == two[i]) scoreTwo++;
            if (answers[i] == three[i]) scoreThree++;
        }
        int max = Math.max(scoreOne, scoreTwo);
        max = Math.max(max, scoreThree);
        ArrayList<Integer> arr = new ArrayList<>();
        if (max == scoreOne) arr.add(1);
        if (max == scoreTwo) arr.add(2);
        if (max == scoreThree) arr.add(3);
        int[] answer = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            answer[i] = arr.get(i);
        }
        return answer;
    }
}