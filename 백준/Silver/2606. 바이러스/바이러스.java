import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] arr = new int[M][2];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        while (true) {
            ArrayList<Integer> temp = new ArrayList<>(list);
            for (int i = 0; i < M; i++) {
                if (list.contains(arr[i][0]) && !list.contains(arr[i][1])) {
                    list.add(arr[i][1]);
                }
            }
            for (int i = 0; i < M; i++) {
                if (list.contains(arr[i][1]) && !list.contains(arr[i][0])) {
                    list.add(arr[i][0]);
                }
            }
            if (temp.equals(list)) break;
        }
        System.out.println(list.size() - 1);
    }
}