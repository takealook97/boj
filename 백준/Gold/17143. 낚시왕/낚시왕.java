import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, M;
    static int fishermanPos = -1;
    static Shark[][] sharkMap;
    static int[] dy = {-1, 1, 0, 0}; // 위, 아래, 오른쪽, 왼쪽
    static int[] dx = {0, 0, 1, -1};
    static int sizeSum = 0;

    static class Shark {
        int speed, dir, size;

        public Shark(int speed, int dir, int size) {
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sharkMap = new Shark[R][C];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            sharkMap[y][x] = new Shark(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken())
            );
        }

        for (int i = 0; i < C; i++) {
        	fishermanPos++;
            catchShark();
            moveSharks();
        }
        System.out.println(sizeSum);
    }

    static void catchShark() {
        for (int y = 0; y < R; y++) {
            if (sharkMap[y][fishermanPos] != null) {
                sizeSum += sharkMap[y][fishermanPos].size;
                sharkMap[y][fishermanPos] = null;
                return;
            }
        }
    }

    static void moveSharks() {
        Shark[][] updatedSharkMap = new Shark[R][C];
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (sharkMap[y][x] != null) {
                    Shark shark = sharkMap[y][x];
                    int[] newPos = getNextPos(y, x, shark);
                    int nextY = newPos[0];
                    int nextX = newPos[1];
                    if (updatedSharkMap[nextY][nextX] == null || updatedSharkMap[nextY][nextX].size < shark.size) {
                        updatedSharkMap[nextY][nextX] = new Shark(shark.speed, newPos[2], shark.size);
                    }
                }
            }
        }
        sharkMap = updatedSharkMap;
    }

    static int[] getNextPos(int curY, int curX, Shark shark) {
        int curSpeed = shark.speed;
        int curDir = shark.dir;

        while (curSpeed-- > 0) {
			int nextY = curY + dy[curDir];
			int nextX = curX + dx[curDir];
			if (0 <= nextY && nextY < R && 0 <= nextX && nextX < C) {
				curY = nextY;
				curX = nextX;
			} else {
				if (curDir % 2 == 0) {
					curDir++;
				} else {
					curDir--;
				}
				curY += dy[curDir];
				curX += dx[curDir];
			}
		}
        return new int[]{curY, curX, curDir};
    }
}
