import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] left = new int[41];
        int[] right = new int[41];
        left[0] = 1;
        left[1] = 0;
        right[0] = 0;
        right[1] = 1;
        for (int i = 2; i <= 40; i++) {
            left[i] = left[i - 1] + left[i - 2];
            right[i] = right[i - 1] + right[i - 2];
        }
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int num = Integer.parseInt(br.readLine());
            bw.write(left[num] + " " + right[num] + '\n');
        }
        bw.flush();
        br.close();
        bw.close();

    }
}
