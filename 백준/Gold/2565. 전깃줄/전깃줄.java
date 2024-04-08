import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Line[] lines;
    static int[] dp;

    static class Line implements Comparable<Line> {
        int from, to;

        Line(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Line o) {
            return this.from - o.from;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new Line[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            lines[i] = new Line(from, to);
        }

        Arrays.sort(lines);

        int max = 0;
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (lines[j].to < lines[i].to && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(N - max);
    }
}
