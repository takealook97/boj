import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int max = 0;
        int height = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(arr[i][j], max);
                height = Math.min(arr[i][j], height);
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int minTime = Integer.MAX_VALUE;
        while (height <= max) {
            int time = 0;
            int block = B;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int gap = height - arr[i][j];
                    if (gap > 0) {//설치
                        block -= gap;
                        time += gap;
                    } else if (gap < 0) {//제거
                        block += (-gap);
                        time += (-gap) * 2;
                    }
                }
            }
            if (block >= 0) {
                if (map.containsKey(time)) {
                    if (map.get(time) < height) {//더 높은 높이로 갱신
                        map.put(time, height);
                    }
                } else {
                    map.put(time, height);
                    minTime = Math.min(time, minTime);//가장 작은 키값 찾기
                }
            }
            height++;
        }
        bw.write(minTime + " " + map.get(minTime));
        bw.flush();
        bw.close();
    }
}