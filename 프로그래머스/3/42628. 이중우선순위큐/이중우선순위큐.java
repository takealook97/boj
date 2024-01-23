import java.util.*;
import java.io.*;

class Solution {
    static ArrayList<Integer> list = new ArrayList<>();
    
    public int[] solution(String[] operations) {
        for(String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            char order = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());
            
            if(order == 'I') {
                insert(num);
            } else {
                if(!list.isEmpty()) {
                    if(num == 1) {
                        list.remove(list.size() - 1);
                    } else {
                        list.remove(0);
                    }
                }
            }
        }
        
        if(list.isEmpty()) {
            return new int[] {0, 0};
        }
        
        return new int[] {list.get(list.size() - 1), list.get(0)};
    }
    
    static void insert(int num) {
		int left = 0;
		int right = list.size() - 1;

		while (left <= right) {
			int mid = left + (right - left) / 2;
			int midNum = list.get(mid);

			if (midNum < num) {
				left = mid + 1;
			} else if (midNum > num) {
				right = mid - 1;
			} else {
				list.add(mid, num);
				return;
			}
		}
		list.add(left, num);
	}
}
