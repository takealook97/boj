import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                makeStars(i, j);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static void makeStars(int i, int j) {
        if (i < 3 && j < 3) {
            if (i % 3 == 1 && j % 3 == 1) {
                sb.append(" ");
            } else {
                sb.append("*");
            }
        } else {
            if (i % 3 == 1 && j % 3 == 1) {
                sb.append(" ");
            } else {
                makeStars(i / 3, j / 3);
            }
        }
    }
}