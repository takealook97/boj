import java.util.*;

class Solution {
    static int K;// 상담 유형 수
    static int N;// 멘토 수 
    static int[] countArr;
    static ArrayList<Client>[] listArr;// 유형별 정렬종류
    static int mentoCount = 0;
    static ArrayList<Client> clients = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;
    
    static class Client implements Comparable<Client> {
        int start, end, idx;
        
        public Client(int start, int end, int idx) {
            this.start = start;
            this.end = start + end;
            this.idx = idx;
        }
        
        @Override
        public int compareTo(Client client) {
            if(this.idx == client.idx) {
                return this.start - client.start;
            }
            return this.idx - client.idx;
        }
        
        @Override
        public String toString() {
            return start + " " + end + " " + idx;
        }
    }
    
    public int solution(int K, int N, int[][] reqs) {
        this.K = K;
        this.N = N;
        countArr = new int[K];
        Arrays.fill(countArr, 1);
        
        // 정렬
        listArr = new ArrayList[K];
        for(int i = 0; i < K; i++) {
            listArr[i] = new ArrayList<>();
        }
        for(int[] req : reqs) {
            listArr[req[2] - 1].add(new Client(req[0], req[1], req[2] - 1));
        }
        for(ArrayList<Client> list : listArr) {
            Collections.sort(list);
        }
        
        // 상담 유형 나누기
        separate(N - K, 0);
        
        
        return answer;
    }
    
    static void separate(int left, int depth) {
        if(left == 0) {
            getMin();
            return;
        }
        if(depth == K) {
            return;
        }
        
        for(int i = 0; i <= left; i++) {
            countArr[depth] += i;
            separate(left - i, depth + 1);
            countArr[depth] -= i;
        }
    }
    
    static void getMin() {
        int sum = 0;
        for(int i = 0; i < K; i++) {// k 개의 상담 별
            mentoCount = countArr[i];
            clients = listArr[i];
            sum += getWaitTime(i);
        }
        answer = Math.min(answer, sum);
    }
    
    static int getWaitTime(int sort) {
        int[] nextAvailableTime = new int[mentoCount];
        Arrays.fill(nextAvailableTime, 0);

        int waitTime = 0;

        for (Client client : clients) {
            int earliestStart = Integer.MAX_VALUE;
            int mentoIndex = -1;
            for (int i = 0; i < mentoCount; i++) {
                if (nextAvailableTime[i] <= client.start && nextAvailableTime[i] < earliestStart) {
                    earliestStart = nextAvailableTime[i];
                    mentoIndex = i;
                }
            }

            if (mentoIndex == -1) {
                int minEndTime = Integer.MAX_VALUE;
                for (int i = 0; i < mentoCount; i++) {
                    if (nextAvailableTime[i] < minEndTime) {
                        minEndTime = nextAvailableTime[i];
                        mentoIndex = i;
                    }
                }

                waitTime += minEndTime - client.start;
                nextAvailableTime[mentoIndex] = minEndTime + (client.end - client.start);
            } else {
                nextAvailableTime[mentoIndex] = client.end;
            }
        }

        return waitTime;
    }

}
