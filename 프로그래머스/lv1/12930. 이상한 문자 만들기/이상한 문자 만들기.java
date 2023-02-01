public class Solution {
    public String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int space = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') space++;
            else break;
        }
        String[] arr = s.split(" ");
        for (int i = 0; i < arr.length; i++) {
            char[] chars = arr[i].toCharArray();
            for (int j = 0; j < arr[i].length(); j++) {
                if (j % 2 == 0) {
                    if (97 <= arr[i].charAt(j) && arr[i].charAt(j) <= 122) {
                        chars[j] = (char) (chars[j] - 32);
                        s = String.valueOf(chars);
                    }
                } else {
                    if (65 <= arr[i].charAt(j) && arr[i].charAt(j) <= 90) {
                        chars[j] = (char) (chars[j] + 32);
                    }
                }
                sb.append(chars[j]);
            }
            if (i != arr.length - 1) {
                sb.append(" ");
            }
        }
        for (int i = 0; i < space; i++) {
            sb.append(" ");
        }
        answer = sb.toString();
        return answer;
    }
}
