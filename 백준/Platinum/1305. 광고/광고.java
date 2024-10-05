import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int L;
    static int[] LPS;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());
        String text = br.readLine();

        build(text);

        System.out.println(L - LPS[L - 1]);
    }

    static void build(String pattern) {
        int length = 0;
        int idx = 1;
        LPS = new int[pattern.length()];

        while (idx < pattern.length()) {
            if (pattern.charAt(idx) == pattern.charAt(length)) {
                length++;
                LPS[idx] = length;
                idx++;
            } else {
                if (length != 0) {
                    length = LPS[length - 1];
                } else {
                    LPS[idx] = 0;
                    idx++;
                }
            }
        }
    }
}
