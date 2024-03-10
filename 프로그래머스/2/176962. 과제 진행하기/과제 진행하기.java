import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class Solution {
    static int N;
    static String[] answer;

    static class Subject {

        String name;
        int time;

        public Subject(String name, int time) {
            this.name = name;
            this.time = time;
        }
    }

    public String[] solution(String[][] plans) {
        N = plans.length;
        answer = new String[N];
        Arrays.sort(plans, Comparator.comparing(o -> o[1]));
        Stack<Subject> stack = new Stack<>();
        int prevTime = 0;
        int idx = 0;
        for (String[] plan : plans) {
            String name = plan[0];
            int startTime = getTime(plan[1]);
            int workingTime = getTime(plan[2]);

            while (!stack.empty()) {
                Subject prevSubject = stack.pop();
                int endTime = prevTime + prevSubject.time;
                if (endTime <= startTime) {
                    answer[idx++] = prevSubject.name;
                    prevTime += prevSubject.time;
                } else {
                    stack.push(new Subject(prevSubject.name, prevSubject.time - (startTime - prevTime)));
                    break;
                }
            }
            stack.push(new Subject(name, workingTime));
            prevTime = startTime;
        }
        while (!stack.empty()) {
            answer[idx++] = stack.pop().name;
        }
        
        return answer;
    }

    private static int getTime(String time) {
        String[] arr = time.split(":");
        if (arr.length == 2) {
            return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
        }
        return Integer.parseInt(time);
    }
}
