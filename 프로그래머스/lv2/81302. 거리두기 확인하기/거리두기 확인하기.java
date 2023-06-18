class Solution {
    static char[][] arr = new char[5][5];
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visit;
    static int[] result = new int[5];
    static int temp;

    public  int[] solution(String[][] places) {
        for (int i = 0; i < 5; i++) {
            String[] lines = places[i];
            for (int j = 0; j < 5; j++) {
                String line = lines[j];
                for (int k = 0; k < 5; k++) {
                    arr[j][k] = line.charAt(k);
                }
            }
            solve();
            result[i] = temp;
        }
        return result;
    }

    static void solve() {
        temp = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (arr[i][j] == 'P') {
                    visit = new boolean[5][5];
                    visit[i][j] = true;
                    if (temp == 0) return;
                    dfs(i, j, 0);
                }
            }
        }
    }

    static void dfs(int y, int x, int depth) {
        if (temp == 0) return;

        if (1 <= depth && depth <= 2) {
            if (arr[y][x] == 'P') {
                temp = 0;
                return;
            }
        } else if (depth > 2) return;
        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (0 <= nextY && nextY < 5 && 0 <= nextX && nextX < 5) {
                if (!visit[nextY][nextX] && arr[nextY][nextX] != 'X') {
                    visit[nextY][nextX] = true;
                    dfs(nextY, nextX, depth + 1);
                    visit[nextY][nextX] = false;
                }
            }
        }
    }
}
