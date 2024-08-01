import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};

    static ArrayList<Set<Point>> list = new ArrayList<>();
    static int answer = 0;

    static final char EMPTY = '#', WALL = '.';

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == EMPTY) {
                    findAllCross(i, j);
                }
            }
        }

        int listSize = list.size();
        for (int i = 0; i < listSize - 1; i++) {
            Set<Point> setA = list.get(i);
            for (int j = i + 1; j < listSize; j++) {
                Set<Point> setB = list.get(j);
                Set<Point> intersection = new HashSet<>(setB);
                intersection.retainAll(setA);
                if (intersection.isEmpty()) {
                    answer = Math.max(answer, setA.size() * setB.size());
                }
            }
        }

        System.out.println(answer);
    }

    static void findAllCross(int midY, int midX) {
        Set<Point> set = new HashSet<>();
        set.add(new Point(midY, midX));
        list.add(set);

        Point[] arr = new Point[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = new Point(midY, midX);
        }

        out:
        while (true) {
            for (int i = 0; i < 4; i++) {
                int nextY = arr[i].y + dy[i];
                int nextX = arr[i].x + dx[i];
                if (isInRange(nextY, nextX) && board[nextY][nextX] == EMPTY) {
                    arr[i].y = nextY;
                    arr[i].x = nextX;
                } else {
                    break out;
                }
            }
            if (arr[0].y == midY) {
                break;
            } else {
                Set<Point> updatedSet = new HashSet<>(list.get(list.size() - 1));
                for (int i = 0; i < 4; i++) {
                    updatedSet.add(new Point(arr[i].y, arr[i].x));
                }
                list.add(updatedSet);
            }
        }
    }

    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
