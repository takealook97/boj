import java.util.*;

class Solution {
    public int[] solution(String s) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        String temp = s.substring(1, s.length() - 1);
        temp = temp.replace("{", "");
        temp = temp.substring(0, temp.length() - 1);
        String[] arr = temp.split("},");

        for (int i = 0; i < arr.length; i++) {
            String cur = arr[i];
            String[] a = cur.split(",");
            ArrayList<Integer> b = new ArrayList<>();
            for (int j = 0; j < a.length; j++) {
                b.add(Integer.parseInt(a[j]));
            }
            pq.add(new Info(b, b.size()));
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            ArrayList<Integer> cur = new ArrayList<>(info.arr);
            cur.removeAll(result);
            int newNum = cur.get(0);
            result.add(newNum);
        }
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    public static class Info implements Comparable<Info> {
        ArrayList<Integer> arr;
        int length;

        public Info(ArrayList<Integer> arr, int length) {
            this.arr = arr;
            this.length = length;
        }

        @Override
        public int compareTo(Info o) {
            return this.length - o.length;
        }
    }
}