import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] group = br.readLine().split("-");
        int result = 0;
        for (int i = 0; i < group.length; i++) {
            int sum = 0;
            String[] plus = group[i].split("\\+");
            for (int j = 0; j < plus.length; j++) {
                sum += Integer.parseInt(plus[j]);
            }
            if (i == 0) result += sum;
            else result -= sum;
        }
        System.out.println(result);
    }
}