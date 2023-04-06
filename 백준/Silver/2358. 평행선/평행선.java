import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> mapX = new HashMap<>();
        HashMap<Integer, Integer> mapY = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (mapX.containsKey(x)) {
                mapX.put(x, mapX.get(x) + 1);
            } else mapX.put(x, 1);
            if (mapY.containsKey(y)) {
                mapY.put(y, mapY.get(y) + 1);
            } else mapY.put(y, 1);
        }
        int count = 0;
        for (int i : mapX.values()) {
            if (i > 1) count++;
        }
        for (int i : mapY.values()) {
            if (i > 1) count++;
        }
        System.out.println(count);
    }
}
