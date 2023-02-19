import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append("I");
        for (int i = 0; i < N; i++) {
            sb.append("O").append("I");
        }
        String P = sb.toString();
        int count = 0;
        for (int i = 0; i <= M - P.length(); i++) {
            if (S.startsWith(P, i)) count++;
        }
        System.out.println(count);
    }
}