import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static boolean[][] visit;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        visit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0) {
                    board[i][j] = getIndex(num);
                    answer = Math.max(answer, board[i][j]);
                } else {
                    board[i][j] = num;
                }
                visit[i][j] = board[i][j] != 0;
            }
        }
        dfs(0);
        System.out.println((int) Math.pow(2, answer));
    }

    static int getIndex(int num) {
        int index = 1;
        while (num / 2 != 1) {
            num /= 2;
            index++;
        }
        return index;
    }

    static void dfs(int depth) {
        if (depth >= 5) {
            return;
        }

        int[][] cur = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(board[i], 0, cur[i], 0, N);
        }
        for (int i = 0; i < 4; i++) {//북 동 남 서
            update(i);
            if (Arrays.deepEquals(cur, board)) {
                continue;
            }
            dfs(depth + 1);
            for (int j = 0; j < N; j++) {
                System.arraycopy(cur[j], 0, board[j], 0, N);
            }
        }
    }

    static void update(int dir) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> line = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                int num = 0;
                if (dir == 0 || dir == 2) {
                    num = board[j][i];
                } else {
                    num = board[i][j];
                }

                if (num != 0) {
                    line.add(num);
                }
            }
            list.add(line);
        }

        gather(list, dir);
    }

    static void gather(ArrayList<ArrayList<Integer>> list, int dir) {
        for (int i = 0; i < N; i++) {
            Deque<Integer> updatedLine = new ArrayDeque<>();
            ArrayList<Integer> line = new ArrayList<>(list.get(i));
            if (line.size() > 1) {
                if (dir == 0 || dir == 3) {//북 서
                    int left = 0, right = 1;
                    while (left < line.size() && right < line.size()) {
                        if (line.get(left).equals(line.get(right))) {
                            updatedLine.add(line.get(left) + 1);
                            left += 2;
                            right += 2;
                        } else {
                            updatedLine.add(line.get(left));
                            left++;
                            right++;
                        }

                        if (left == line.size() - 1) {
                            updatedLine.add(line.get(left));
                            break;
                        }
                    }
                } else {//동 남
                    int left = line.size() - 2, right = line.size() - 1;
                    while (left >= 0 && right >= 0) {
                        if (line.get(left).equals(line.get(right))) {
                            updatedLine.addFirst(line.get(right) + 1);
                            left -= 2;
                            right -= 2;
                        } else {
                            updatedLine.addFirst(line.get(right));
                            left--;
                            right--;
                        }

                        if (right == 0) {
                            updatedLine.addFirst(line.get(right));
                            break;
                        }
                    }
                }
            } else if (line.size() == 1) {
                updatedLine.add(line.get(0));
            }

            while (updatedLine.size() < N) {
                if (dir == 0 || dir == 3) {
                    updatedLine.addLast(0);
                } else {
                    updatedLine.addFirst(0);
                }
            }

            for (int j = 0; j < N; j++) {
                int num = updatedLine.removeFirst();
                if (dir == 0 || dir == 2) {
                    board[j][i] = num;
                } else {
                    board[i][j] = num;
                }
                answer = Math.max(answer, num);
            }
        }
    }
}
