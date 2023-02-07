import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "add":
                    int addNum = Integer.parseInt(st.nextToken());
                    if (!list.contains(addNum)) {
                        list.add(addNum);
                    }
                    break;
                case "remove":
                    int removeNum = Integer.parseInt(st.nextToken());
                    if (list.contains(removeNum)) {
                        list.remove(Integer.valueOf(removeNum));
                    }
                    break;
                case "check":
                    int checkNum = Integer.parseInt(st.nextToken());
                    if (list.contains(checkNum)) sb.append(1 + "\n");
                    else sb.append(0 + "\n");
                    break;
                case "toggle":
                    int toggleNum = Integer.parseInt(st.nextToken());
                    if (!list.contains(toggleNum)) {
                        list.add(toggleNum);
                    } else list.remove(Integer.valueOf(toggleNum));
                    break;
                case "all":
                    list.clear();
                    for (int j = 1; j <= 20; j++) {
                        list.add(j);
                    }
                    break;
                case "empty":
                    list.clear();
                    break;
            }
        }
        System.out.print(sb);
    }
}