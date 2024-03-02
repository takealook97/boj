import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        List<String> list = new ArrayList<>();
        for(int num : numbers) {
            list.add(String.valueOf(num));
        }
        list.sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        StringBuilder sb = new StringBuilder();
        for(String num : list) {
            sb.append(num);
        }
        if(sb.charAt(0) == '0') return "0";
        return sb.toString();
    }
}
