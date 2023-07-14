import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] arr;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            checkLine(arr[i]);
            checkLine(getColumn(i));
        }
        System.out.println(count);
    }

    static void checkLine(int[] line) {
        boolean[] visited = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            if (line[i] == line[i + 1]) {
                continue;
            } else if (Math.abs(line[i] - line[i + 1]) > 1) {
                return;
            } else {
                if (line[i] < line[i + 1]) {
                    for (int j = i; j > i - L; j--) {
                        if (j < 0 || line[j] != line[i] || visited[j]) {
                            return;
                        }
                        visited[j] = true;
                    }
                } else {
                    for (int j = i + 1; j <= i + L; j++) {
                        if (j >= N || line[j] != line[i + 1] || visited[j]) {
                            return;
                        }
                        visited[j] = true;
                    }
                }
            }
        }
        count++;
    }

    static int[] getColumn(int index) {
        int[] column = new int[N];
        for (int i = 0; i < N; i++) {
            column[i] = arr[i][index];
        }
        return column;
    }
}
