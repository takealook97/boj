import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][][] visited;
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static ArrayList<Point> airList = new ArrayList<>();
    static int answer = 0;

    static final int AIR = 9;

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == AIR) {
                    airList.add(new Point(i, j));
                }
            }
        }

        for (Point point : airList) {
            for (int i = 0; i < 4; i++) {
                getCount(point.y, point.x, i);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    if (visited[i][j][dir]) {
                        answer++;
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static void getCount(int nowY, int nowX, int nowDir) {
        if (!isInRange(nowY, nowX) || visited[nowY][nowX][nowDir]) {
            return;
        }

        visited[nowY][nowX][nowDir] = true;

        int nextDir = getNextDir(board[nowY][nowX], nowDir);
        if (nextDir == -1) {
            return;
        }

        getCount(nowY + dy[nextDir], nowX + dx[nextDir], nextDir);
    }

    static int getNextDir(int status, int dir) {
        if (status == 1) {
            if (dir == 0 || dir == 2) {
                return dir;
            } else {
                return -1;
            }
        } else if (status == 2) {
            if (dir == 1 || dir == 3) {
                return dir;
            } else {
                return -1;
            }
        } else if (status == 3) {
            if (dir == 0) {
                return 1;
            } else if (dir == 1) {
                return 0;
            } else if (dir == 2) {
                return 3;
            } else if (dir == 3) {
                return 2;
            }
        } else if (status == 4) {
            if (dir == 0) {
                return 3;
            } else if (dir == 1) {
                return 2;
            } else if (dir == 2) {
                return 1;
            } else if (dir == 3) {
                return 0;
            }
        }
        return dir;
    }

    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
