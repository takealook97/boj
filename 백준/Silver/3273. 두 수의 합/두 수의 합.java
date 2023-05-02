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
        ArrayList<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        int X = Integer.parseInt(br.readLine());
        Collections.sort(list);
        int count = 0;
        int left = 0;
        int right = N - 1;
        while (left < right) {
            if (list.get(left) + list.get(right) == X) {
                count++;
                left++;
            } else if (list.get(left) + list.get(right) < X) {
                left++;
            } else right--;
        }
        System.out.println(count);
    }
}