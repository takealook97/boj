import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());//가로
        N = Integer.parseInt(st.nextToken());//세로
        int[][] arr = new int[N][M];
        HashSet<Location> list = new HashSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) list.add(new Location(i, j));
            }
        }
        int count = 0;

        while (true) {
            HashSet<Location> temp = new HashSet<>();
            for (Location location : list) {
                int i = location.x;
                int j = location.y;
                if (0 <= i - 1) {
                    if (arr[i - 1][j] == 0) {
                        temp.add(new Location(i - 1, j));
                    }
                }
                if (i + 1 <= N - 1) {
                    if (arr[i + 1][j] == 0) {
                        temp.add(new Location(i + 1, j));
                    }
                }
                if (0 <= j - 1) {
                    if (arr[i][j - 1] == 0) {
                        temp.add(new Location(i, j - 1));
                    }
                }
                if (j + 1 <= M - 1) {
                    if (arr[i][j + 1] == 0) {
                        temp.add(new Location(i, j + 1));
                    }
                }
            }//맵에 한바퀴 다 돌고 바꿀 인덱스 넣어놓은 상태

            if (temp.isEmpty()) break; //바꿀게 없는 경우
            else {//바꿀게 있을경우
                for (Location temporary : temp) {
                    arr[temporary.x][temporary.y] = 1;//인덱스값에 해당하는 수 모두 1로 변경
                }
            }
            count++;//카운트 증가
            list = temp;

        }
        if (!checkArray(arr)) System.out.println(-1);
        else System.out.println(count);
    }

    static boolean checkArray(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void printArray(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Location) {
            return this.x == ((Location) obj).x && this.y == ((Location) obj).y;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}