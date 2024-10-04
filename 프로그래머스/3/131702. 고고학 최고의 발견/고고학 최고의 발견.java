import java.util.*;

class Solution {
    static int N;
    static int[] dy = {0, 0, 0, -1, 1}, dx = {0, 1, -1, 0, 0};
    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] clockHands) {
        N = clockHands.length;
        int maxComb = (int) Math.pow(4, N);
        for (int comb = 0; comb < maxComb; comb++) {
            answer = Math.min(answer, find(clockHands, comb));
        }
        return answer;
    }

    static int find(int[][] clockHands, int comb) {
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = clockHands[i].clone();
        }

        int moveCount = 0;
        for (int i = 0; i < N; i++) {
            int rotations = (comb / (int) Math.pow(4, i)) % 4;
            moveCount += rotations;
            for (int r = 0; r < rotations; r++) {
                spin(board, 0, i);
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int rotations = (4 - board[i - 1][j]) % 4;
                moveCount += rotations;
                for (int r = 0; r < rotations; r++) {
                    spin(board, i, j);
                }
            }
        }

        for (int j = 0; j < N; j++) {
            if (board[N - 1][j] != 0) {
                return Integer.MAX_VALUE;
            }
        }

        return moveCount;
    }

    static void spin(int[][] board, int y, int x) {
        for (int i = 0; i < 5; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (isInRange(nextY, nextX)) {
                board[nextY][nextX] = (board[nextY][nextX] + 1) % 4;
            }
        }
    }

    static boolean isInRange(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
