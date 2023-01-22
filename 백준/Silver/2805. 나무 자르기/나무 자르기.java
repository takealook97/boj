import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int low = 0;
        int high = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(sc.nextInt());
            if (high <= arr.get(i)) high = arr.get(i);
        }
        while (low < high) {
            int mid = (low + high) / 2;
            long sum = 0;

            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i) - mid > 0) {
                    sum += (arr.get(i) - mid);
                }
            }

            if (sum < M) high = mid;
            else low = mid + 1;
        }
        System.out.println(low - 1);
    }
}