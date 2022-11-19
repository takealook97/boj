import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(stk.nextToken());
            int num2 = Integer.parseInt(stk.nextToken());
            bw.write(num1 + num2 + "\n");
        }
        bw.flush();
        bw.close();
    }
}

