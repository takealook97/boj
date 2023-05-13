import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Boolean> list = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String info = st.nextToken();
            if (info.equals("enter")) list.put(name, true);
            else list.remove(name);
        }
        ArrayList<String> result = new ArrayList<>(list.keySet());
        Collections.sort(result, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String name : result) {
            sb.append(name).append("\n");
        }
        System.out.print(sb);
    }
}