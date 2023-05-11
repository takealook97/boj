import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] cities;
    static int[] stations;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cities = new int[N - 1];
        stations = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            stations[i] = Integer.parseInt(st.nextToken());
        }
        int location = 0;
        long gas = stations[location];
        while (location <= N - 2) {
            result += gas * cities[location];
            if (gas > stations[location + 1]) {
                gas = stations[location + 1];
            }
            location++;
        }
        System.out.println(result);
    }
}
