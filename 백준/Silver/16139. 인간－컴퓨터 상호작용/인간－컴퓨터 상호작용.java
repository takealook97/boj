import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String input = br.readLine();
            String[] arr = input.split(" ");
            char alphabet = input.charAt(0);
            int start = Integer.parseInt(arr[1]);
            int end = Integer.parseInt(arr[2]);
            String temp = line.substring(start, end + 1);
            ArrayList<Character> list = new ArrayList<>();
            for (int j = 0; j < temp.length(); j++) {
                list.add(temp.charAt(j));
            }
            int result = Collections.frequency(list, Character.valueOf(alphabet));
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
