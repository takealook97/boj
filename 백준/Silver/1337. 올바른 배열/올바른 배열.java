import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }

        int result = 4;
        for (int i = 0; i < N; i++) {
            int a = arr.get(i);
            int count = 1;
            if (arr.contains(a + 1)) count++;
            if (arr.contains(a + 2)) count++;
            if (arr.contains(a + 3)) count++;
            if (arr.contains(a + 4)) count++;
            if (count == 5) {
                System.out.println(0);
                System.exit(0);
            } else {
                result = Math.min(result, 5 - count);
            }
        }
        System.out.println(result);
    }
}