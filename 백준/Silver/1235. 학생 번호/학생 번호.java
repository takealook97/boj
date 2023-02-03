import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        String[] part = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        int length = arr[0].length();
        int num = arr[0].length() - 1;
        while (true) {
            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = num; j < arr[0].length(); j++) {
                    sb.append(arr[i].charAt(j));
                }
                part[i] = sb.toString();
            }
            HashSet<String> set = new HashSet<>();
            for (int i = 0; i < N; i++) {
                set.add(part[i]);
            }
            if (set.size() != N) {
                num--;
            } else break;
        }
        System.out.println(length - num);
    }
}