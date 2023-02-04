import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<Integer, String> arrA = new HashMap<>();
        HashMap<String, Integer> arrB = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            arrA.put(i + 1, name);
            arrB.put(name, i + 1);

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if (arrB.containsKey(input)) sb.append(arrB.get(input)).append("\n");
            else sb.append(arrA.get(Integer.parseInt(input))).append("\n");
        }
        System.out.println(sb);
    }
}