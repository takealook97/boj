import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            if (!map.containsKey(name)) {
                map.put(name, 1);
            } else {
                map.put(name, map.get(name) + 1);
            }
        }
        ArrayList<String> names = new ArrayList<>();
        int maxNum = 0;
        out:
        for (int i = N; i >= 0; i--) {
            for (int j : map.values()) {
                if (i == j) {
                    maxNum = j;
                    break out;
                }
            }
        }
        for (String name : map.keySet()) {
            if (map.get(name) == maxNum) {
                names.add(name);
            }
        }
        Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
        System.out.println(names.get(0));
    }
}