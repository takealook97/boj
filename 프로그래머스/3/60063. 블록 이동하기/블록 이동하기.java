import java.util.*;

class Solution {
    static int N;
    static int[][] board;
    static HashSet<Robot> visit = new HashSet<>();
    static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
    static Point dest;
    static int answer = Integer.MAX_VALUE;

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

    static class Robot implements Comparable<Robot> {
        Point p1, p2;
        int count;

        public Robot(Point p1, Point p2, int count) {
            this.p1 = p1;
            this.p2 = p2;
            this.count = count;
        }

        @Override
        public int compareTo(Robot robot) {
            return Integer.compare(this.count, robot.count);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Robot robot = (Robot) o;
            return p1.equals(robot.p1) && p2.equals(robot.p2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(p1, p2);
        }
    }

    public int solution(int[][] arr) {
        N = arr.length;
        board = arr;
        dest = new Point(N - 1, N - 1);

        getMin(new Robot(new Point(0, 0), new Point(0, 1), 0));

        return answer;
    }

    static void getMin(Robot start) {
        PriorityQueue<Robot> queue = new PriorityQueue<>();
        queue.add(start);
        visit.add(start);

        while (!queue.isEmpty()) {
            Robot now = queue.poll();

            if (now.count >= answer) {
                continue;
            }

            if (now.p1.equals(dest) || now.p2.equals(dest)) {
                answer = Math.min(answer, now.count);
                continue;
            }

            for (Robot next : getNext(now)) {
                if (visit.add(next)) {
                    queue.add(next);
                }
            }
        }
    }

    static ArrayList<Robot> getNext(Robot robot) {
        ArrayList<Robot> list = new ArrayList<>();
        Point p1 = robot.p1, p2 = robot.p2;

        // Move
        for (int i = 0; i < 4; i++) {
            Point nextP1 = new Point(p1.y + dy[i], p1.x + dx[i]);
            Point nextP2 = new Point(p2.y + dy[i], p2.x + dx[i]);
            if (isPossible(nextP1) && isPossible(nextP2)) {
                list.add(new Robot(nextP1, nextP2, robot.count + 1));
            }
        }

        // Rotation
        list.addAll(getRotations(p1, p2, robot.count));
        list.addAll(getRotations(p2, p1, robot.count));

        return list;
    }

    static ArrayList<Robot> getRotations(Point p1, Point p2, int count) {
        ArrayList<Robot> rotations = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int ny = p2.y + dy[i], nx = p2.x + dx[i];
            if (isPossible(ny, nx)) {
                Point newPoint = new Point(p1.y + dy[i], p1.x + dx[i]);
                if (isPossible(newPoint)) {
                    rotations.add(new Robot(p1, newPoint, count + 1));
                }
            }
        }
        return rotations;
    }

    static boolean isPossible(Point point) {
        return 0 <= point.y && point.y < N && 0 <= point.x && point.x < N && board[point.y][point.x] != 1;
    }

    static boolean isPossible(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N && board[y][x] != 1;
    }
}
