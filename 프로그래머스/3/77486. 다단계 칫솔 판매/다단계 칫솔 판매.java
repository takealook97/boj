import java.util.*;

class Solution {
    static int N;
    static String[] enroll;
    static int[] fromArr;
    static int[] answer;
    static final String NONE = "-";
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        N = enroll.length;
        this.enroll = enroll;
        
        fromArr = new int[N + 1];
        
        int headIdx = 0;
        for(int i = 0; i < N; i++) {
            if(referral[i].equals(NONE)) {
                headIdx = 0;
            } else {
                headIdx = getNameIdx(referral[i]);
            }
            
            fromArr[i + 1] = headIdx;
        }
        
        answer = new int[N];
        for(int i = 0; i < seller.length; i++) {
            int sellerIdx = getNameIdx(seller[i]);
            int price = amount[i] * 100;
            addAccum(sellerIdx, price);
        }
        
        return answer;
    }
    
    static int getNameIdx(String name) {// idx + 1
        for(int i = 0; i < N; i++) {
            if(enroll[i].equals(name)) {
                return i + 1;
            }
        }
        return -1;
    }
    
    static void addAccum(int now, int priceLeft) {
        if(now == 0 || priceLeft == 0) {
            return;
        }
        
        int nextPrice = priceLeft / 10;
        answer[now - 1] += priceLeft - nextPrice;
        addAccum(fromArr[now], nextPrice);
    }
}
