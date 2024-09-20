import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, H;
    static int[] arrA, arrB;
    static int answer = Integer.MAX_VALUE, count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arrA = new int[N / 2];
        arrB = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            arrA[i] = Integer.parseInt(br.readLine());
            arrB[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arrA);
        Arrays.sort(arrB);

        int sum;
        for (double i = 0.5; i < (double) H; i++) {
            sum = 0;
            sum += (N / 2 - lowerBound(arrA, i, false));
            sum += (N / 2 - lowerBound(arrB, i, true));
            if (sum < answer) {
                answer = sum;
                count = 1;
            } else if (sum == answer) {
                count++;
            }
        }

        System.out.println(answer + " " + count);
    }

    static int lowerBound(int[] arr, double target, boolean isUpward) {
        int lo = 0;
        int hi = N / 2 - 1;
        int mid;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;

            if (!isUpward) {
                if (arr[mid] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
                continue;
            }

            if (H - arr[mid] <= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }
}
