import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);

        long min = 2_100_000_000;
        int left = 0;
        int right = N - 1;
        long l = 0, r = 0, movedL = 0, movedR = 0, sum = 0;
        while (left < right) {
            movedL = Math.abs(arr.get(left + 1) + arr.get(right));
            movedR = Math.abs(arr.get(left) + arr.get(right - 1));
            sum = Math.abs(arr.get(left) + arr.get(right));
            int tempL = left;
            int tempR = right;

            if (sum == 0) {
                System.out.println(arr.get(left) + " " + arr.get(right));
                System.exit(0);
            }
            if (sum < min) {
                min = sum;
                l = arr.get(left);
                r = arr.get(right);
                if (movedL <= movedR) {
                    left++;
                } else {
                    right--;
                }
            } else {
                if (movedL <= movedR) {
                    left++;
                } else right--;
            }

            if (left == tempL && right == tempR) break;
        }
        System.out.println(l + " " + r + "\n");
    }
}