import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int m = Integer.parseInt(br.readLine());
            if (m == 0) {
                System.out.println(0);
                continue;
            }
            String[] arr = new String[m];
            for (int j = 0; j < m; j++) {
                String temp = br.readLine();
                arr[j] = temp.substring(temp.lastIndexOf(" ") + 1);
            }
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < m; j++) {
                if (!map.containsKey(arr[j])) {
                    map.put(arr[j], 1);
                } else {
                    map.put(arr[j], map.get(arr[j]) + 1);
                }
            }
            int sum = 1;
            for (int x : map.values()) {
                sum *= (x + 1);
            }
            System.out.println(sum - 1);

        }
    }
}