import java.util.*;

class Solution {
    static int N;
    static int[] weakArr;
    static int[] distArr;
    static ArrayList<Integer> distList = new ArrayList<>();
    static boolean[] visit;
    static boolean[] distCheck;
    static int answer = -1;
    
    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        N = n;
        weakArr = weak;
        distArr = dist;
        visit = new boolean[N];
        distCheck = new boolean[dist.length];
        
        Arrays.fill(visit, true);
        for(int i : weakArr) {
            visit[i] = false;
        }
        
        perm();
        
        return answer;
    }
    
    static void perm() {
        for(int i = distArr.length - 1; i >= 0; i--) {
            distList.add(distArr[i]);
            for(int j : weakArr) {
                getMin(j, 0);
            }
        }
    }
    
    static void getMin(int start, int count) {
        if(answer != -1) {
            return;
        }

        if(isFinished()) {
            answer = count;
            return;
        }

        while(true) {
            if(!visit[start]) {
                break;
            }
            start++;
            if(start >= N) {
                start -= N;
            }
        }

        for(int i = 0; i < distList.size(); i++) {
            int distValue = distList.get(i);
            if(!distCheck[i]) {
                distCheck[i] = true;
                ArrayList<Integer> list = new ArrayList<>();
                for(int j = start; j <= start + distValue; j++) {
                    if(!visit[j % N]) {
                        visit[j % N] = true;
                        list.add(j % N);
                    }
                }

                getMin(start, count + 1);

                for(int j : list) {
                    visit[j] = false;
                }

                distCheck[i] = false;
            }
        }
    }

    
    static boolean isFinished() {
        for(boolean i : visit) {
            if(!i) {
                return false;
            }
        }
        return true;
    }
}
