import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        boolean checkFirst = false;
        int firstIndex = 0;

        int sum = 0;
        int minLength = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            sum += input;
            arr[i] = sum;

            if (!checkFirst && sum >= S) {
                firstIndex = i;
                checkFirst = true;
                minLength = i + 1;
            }
            if (input == S) {
                System.out.println(1);
                System.exit(0);
            }
        }
        if(!checkFirst){
            System.out.println(0);
            System.exit(0);
        }

        int left = 0;
        int right = firstIndex;
        while (left < right) {
            if (minLength == 1) {
                System.out.println(1);
                System.exit(0);
            }

            long X = arr[right] - arr[left];
            if (X < S) {
                if (right < N - 1) right++;
                else left++;
            } else if (X >= S) {
                minLength = Math.min(minLength, right - left);
                left++;
            }
        }
        System.out.println(minLength);
    }
}
