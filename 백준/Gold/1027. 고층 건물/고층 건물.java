import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] buildings;
    static int[] visible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        visible = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        count();
        Arrays.sort(visible);
        System.out.println(visible[visible.length - 1]);
    }

    static void count() {
        for (int i = 0; i < N - 1; i++) {
            visible[i]++;
            visible[i + 1]++;
            double slope = buildings[i + 1] - buildings[i];
            for (int j = i + 2; j < N; j++) {
                double nextSlope = (double) (buildings[j] - buildings[i]) / (j - i);
                if (nextSlope > slope) {
                    slope = nextSlope;
                    visible[i]++;
                    visible[j]++;
                }
            }
        }
    }
}
