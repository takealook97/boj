import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            for (int i = 0; i < k; i++) {
                String[] order = br.readLine().split(" ");
                if (order[0].equals("I")) {// I
                    int num = Integer.parseInt(order[1]);
                    tm.put(num, tm.getOrDefault(num, 0) + 1);
                } else if (order[0].equals("D")) {// D
                    if (tm.size() != 0) {
                        int key = 0;
                        if (Integer.parseInt(order[1]) == 1) key = tm.lastKey();
                        else key = tm.firstKey();
                        int value = tm.get(key);
                        if (value == 1) tm.remove(key);
                        else tm.put(key, value - 1);
                    }
                }
            }
            if (tm.isEmpty()) sb.append("EMPTY").append("\n");
            else {
                sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append("\n");
            }
        }
        System.out.println(sb);
    }
}