import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static HashSet<Integer> set = new HashSet<>();
    static ArrayList<Integer> target = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (N-- > 0) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            target.add(Integer.parseInt(st.nextToken()));
        }
        StringBuilder sb = new StringBuilder();
        for (int i : target) {
            if (set.contains(i)) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append(" ");
        }
        System.out.print(sb);
    }
}
