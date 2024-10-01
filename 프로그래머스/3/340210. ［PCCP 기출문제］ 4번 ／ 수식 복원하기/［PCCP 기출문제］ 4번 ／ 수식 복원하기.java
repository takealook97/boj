import java.util.*;

class Solution {
    static int N;
    static String[][] arr;
    static List<Integer> idxList = new ArrayList<>();
    static boolean[] isPossible;
    static String curAnswer;
    static boolean isChanged;
    
    static String[] answer;
    
    static final int ORDER = 1, FIRST = 0, SECOND = 2, RESULT = 4;
    static final String BLANK = "X", UNKNOWN = "?", PLUS = "+", MINUS = "-";
    
    public String[] solution(String[] expressions) {
        N = expressions.length;
        arr = new String[N][5];
        isPossible = new boolean[10];
        for (int i = 2; i <= 9; i++) {
            isPossible[i] = true;
        }
        
        int maxDigit = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = expressions[i].split(" ");
            for(int idx = 0; idx < 5; idx++) {
                if(idx == RESULT && arr[i][idx].equals(BLANK)) {
                    idxList.add(i);
                }
                
                if(idx == FIRST || idx == SECOND || idx == RESULT) {
                    if(arr[i][idx].equals(BLANK)) {
                        continue;
                    }
                    
                    String numStr = arr[i][idx];
                    for (int k = 0; k < numStr.length(); k++) {
                        char c = numStr.charAt(k);
                        if (c >= '0' && c <= '9') {
                            int digitVal = c - '0';
                            maxDigit = Math.max(maxDigit, digitVal);
                        }
                    }
                }
            }
        }
        
        for(int i = 2; i <= maxDigit; i++) {
            isPossible[i] = false;
        }
        
        search();
        
        setAnswer();
        
        return answer;
    }
    
    static void search() {
        for (int idx = 0; idx < N; idx++) {
            if (!idxList.contains(idx)) {
                calculate(idx);
            }
        }
    }
    
    static void calculate(int idx) {
        int value = 0;
        
        for (int digit = 2; digit <= 9; digit++) {
            if (isPossible[digit]) {
                calculateLine(idx, digit, true);
            }
        }
    }
    
    static void calculateLine(int idx, int digit, boolean isSetting) {
        int first = 0, second = 0, result = 0;
        
        String numStr = arr[idx][FIRST];
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            int digitVal = c - '0';
            if (digitVal >= digit) {
                if (isSetting) {
                    isPossible[digit] = false;
                } else {
                    isChanged = true;
                }
                return;
            }
            first = first * digit + digitVal;
        }

        numStr = arr[idx][SECOND];
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            int digitVal = c - '0';
            if (digitVal >= digit) {
                if (isSetting) {
                    isPossible[digit] = false;
                } else {
                    isChanged = true;
                }
                return;
            }
            second = second * digit + digitVal;
        }
        
        if(isSetting) {
            numStr = arr[idx][RESULT];
            for (int i = 0; i < numStr.length(); i++) {
                char c = numStr.charAt(i);
                int digitVal = c - '0';
                if (digitVal >= digit) {
                    isPossible[digit] = false;
                    return;
                }
                result = result * digit + digitVal;
            }
        }
    
        if(!isSetting) {
            int tempResult = 0;
            if(arr[idx][ORDER].equals(PLUS)) {
                tempResult = first + second;
            } else {// minus
                tempResult = first - second;
            }
            
            String tempResultStr = Integer.toString(tempResult, digit);
            for (int i = 0; i < tempResultStr.length(); i++) {
                char c = tempResultStr.charAt(i);
                int digitVal = c - '0';
                if (digitVal >= digit) {
                    isChanged = true;
                    return;
                }
            }
            
            if(curAnswer == null) {
                curAnswer = tempResultStr;
            } else {
                if(!curAnswer.equals(tempResultStr)) {
                    isChanged = true;
                }
            }
            
            return;
        }
        
        if (arr[idx][ORDER].equals(PLUS)) {
            if(first + second != result) {
                isPossible[digit] = false;
            }
        } else {// MINUS
            if(first - second != result) {
                isPossible[digit] = false;
            }
        }
    }
    
    static void setAnswer() {
        for (int idx : idxList) {
            curAnswer = null;
            isChanged = false;
            for (int digit = 2; digit <= 9; digit++) {
                if(isPossible[digit]) {
                    calculateLine(idx, digit, false);
                    if(isChanged) {
                        arr[idx][RESULT] = UNKNOWN;
                        break;
                    }
                }
            }
            
            if (curAnswer == null || isChanged) {
                arr[idx][RESULT] = UNKNOWN;
            } else {
                arr[idx][RESULT] = curAnswer;
            }
        }
        
        answer = new String[idxList.size()];
        StringBuilder sb;
        int i = 0;
        for(int idx : idxList) {
            sb = new StringBuilder();
            sb.append(arr[idx][FIRST]).append(" ")
                .append(arr[idx][ORDER]).append(" ")
                .append(arr[idx][SECOND])
                .append(" = ").append(arr[idx][RESULT]);
            answer[i++] = sb.toString();
        }
    }
}
