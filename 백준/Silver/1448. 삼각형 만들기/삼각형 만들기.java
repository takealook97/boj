import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(arr);
        int index = 1;
        while (true) {
            int a = arr.get(arr.size() - index);
            int b = arr.get(arr.size() - (index + 1));
            int c = arr.get(arr.size() - (index + 2));
            if (a < b + c) {
                System.out.println(a + b + c);
                break;
            } else {
                index++;
                if (arr.size() - (index + 2) < 0) {
                    System.out.println(-1);
                    break;
                }
            }
        }
    }
}