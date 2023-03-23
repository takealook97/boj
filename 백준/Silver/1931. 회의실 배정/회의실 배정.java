import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));
        //가장 빨리끝나면서 동시에 직전의 종료시각과 겹치지 않는 시간 찾기
        int count = 1;
        int end = arr[0][1];
        for (int i = 1; i < N; i++) {
            if (end <= arr[i][0]) {
                end = arr[i][1];
                count++;
            }
        }
        System.out.println(count);
    }
}