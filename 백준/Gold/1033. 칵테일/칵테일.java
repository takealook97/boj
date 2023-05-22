import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer>[] arr;
    static long[] result;
    static boolean[] visit;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            System.exit(0);
        }
        arr = new ArrayList[N];
        result = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            long inputGCD = getGCD(p, q);
            p /= inputGCD;
            q /= inputGCD;
            visit = new boolean[N];

            if (result[a] == 0 && result[b] == 0) {
                result[a] = p;
                result[b] = q;
            } else if (result[a] != 0 && result[b] == 0) {
                result[b] = result[a] * q;
                multipleNodes(a, p);
            } else if (result[a] == 0 && result[b] != 0) {
                result[a] = result[b] * p;
                multipleNodes(b, q);
            } else if (result[a] != 0 && result[b] != 0) {
                long lcm = getLCM(result[a], result[b]);
                long left = lcm * p / result[a];
                long right = lcm * q / result[b];
                multipleNodes(a, left);
                multipleNodes(b, right);
            }
            arr[a].add(b);
            arr[b].add(a);
        }
        long gcd = result[0];
        for (long i : result) {
            gcd = getGCD(gcd, i);
        }
        for (int i = 0; i < N; i++) {
            result[i] /= gcd;
        }
        for (long i : result) {
            System.out.print(i + " ");
        }
    }

    static long getGCD(long a, long b) {
        if (b == 0) return a;
        return getGCD(b, a % b);
    }

    static long getLCM(long a, long b) {
        return a * b / getGCD(a, b);
    }

    static void multipleNodes(int node, long time) {
        for (Integer i : arr[node]) {
            if (!visit[i]) {
                result[i] *= time;
                visit[i] = true;
                multipleNodes(i, time);
            }
        }
    }
}
