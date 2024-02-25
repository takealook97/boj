import java.util.*;

class Solution {
    static int[] countArr = new int[10];
    static int[] tempArr = new int[10];
    static int answer = 0;
    
    	public int solution(String numbers) {
		for(int i = 0; i < numbers.length(); i++) {
			countArr[numbers.charAt(i) - '0']++;
		}
		copyArr();

		StringBuilder sb = new StringBuilder();
		for(int i = 9; i >= 0; i--) {
			while(tempArr[i] > 0) {
				sb.append(i);
				tempArr[i]--;
			}
		}
		int maxNum = Integer.parseInt(sb.toString());

		for(int i = 2; i <= maxNum; i++) {
			if(isPossible(i) && isPrime(i)) {
				answer++;
			}
		}


		return answer;
	}

	static void copyArr() {
		System.arraycopy(countArr, 0, tempArr, 0, 10);
	}
    
    static boolean isPossible(int number) {
        copyArr();
        
        while(number > 0) {
            int target = number % 10;
            if(tempArr[target] > 0) {
                tempArr[target]--;
                number /= 10;
            } else {
                return false;
            }
        }
        return true;
        
    }
    
    static boolean isPrime(int number) {
        for (int i = 2; i <= (int) Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
	    }
	    return true;
    }

}
