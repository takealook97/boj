import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int N;
    static ArrayList<Integer> arr = new ArrayList<>();
    static boolean checkZero;
    static int zeroIndex;
    static int leftCount;
    static int rightCount;
    static boolean isLeftEven;
    static boolean isRightEven;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            arr.add(num);
            if (num < 0) leftCount++;
            else if (num > 0) rightCount++;
        }
        arr.sort(Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            if (arr.contains(0) && arr.get(i) == 0) {
                checkZero = true;
                zeroIndex = i;
                break;
            }
        }
        if (leftCount % 2 == 0) isLeftEven = true;
        if (rightCount % 2 == 0) isRightEven = true;
        if (N == 1) {
            System.out.println(arr.get(0));
            System.exit(0);
        }
        getSum();
        System.out.println(sum);
    }

    static void getSum() {
        for (int i = 0; i < N; i += 2) {
            if (i == N - 1 && arr.get(i) > 0) sum += arr.get(i);
            else if (arr.get(i) == 1 && arr.get(i + 1) > 0) {
                sum += arr.get(i) + arr.get(i + 1);
            } else if (arr.get(i) == 1 && arr.get(i + 1) <= 0) {
                sum += arr.get(i);
            } else if (arr.get(i) > 0 && arr.get(i + 1) == 1) {
                sum += arr.get(i) + arr.get(i + 1);
            } else if (arr.get(i) > 0 && arr.get(i + 1) > 0) {
                sum += arr.get(i) * arr.get(i + 1);
            } else if (arr.get(i) > 0 && arr.get(i + 1) <= 0) {
                sum += arr.get(i);
            } else break;
//            System.out.println(sum);
        }
        Collections.sort(arr);
        for (int i = 0; i < N; i += 2) {
            if (arr.get(i) >= 0) break;
            if (i == N - 1 && arr.get(i) < 0) sum += arr.get(i);
            else if (arr.get(i) < 0 && arr.get(i + 1) < 0) {
                sum += arr.get(i) * arr.get(i + 1);
            } else if (arr.get(i) < 0 && arr.get(i + 1) > 0) {
                sum += arr.get(i);
            } else break;
//            System.out.println(sum);
        }
    }
}