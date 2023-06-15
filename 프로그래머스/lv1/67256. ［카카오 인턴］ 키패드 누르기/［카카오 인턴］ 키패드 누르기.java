class Solution {
    static Point leftHand = new Point(3, 0);
    static Point rightHand = new Point(3, 2);

    static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        for (int num : numbers) {
            Point position = getPosition(num);
            int nextY = position.y;
            int nextX = position.x;
            if (nextX == 0) {
                leftHand = new Point(nextY, nextX);
                sb.append("L");
            } else if (nextX == 2) {
                rightHand = new Point(nextY, nextX);
                sb.append("R");
            } else {
                int leftD = getDistance(leftHand, nextY, nextX);
                int rightD = getDistance(rightHand, nextY, nextX);
                if (leftD < rightD) {
                    leftHand = new Point(nextY, nextX);
                    sb.append("L");
                } else if (leftD > rightD) {
                    rightHand = new Point(nextY, nextX);
                    sb.append("R");
                } else {
                    if (hand.equals("left")) {
                        leftHand = new Point(nextY, nextX);
                        sb.append("L");
                    } else if (hand.equals("right")) {
                        rightHand = new Point(nextY, nextX);
                        sb.append("R");
                    }
                }
            }
        }
        return sb.toString();
    }

    public Point getPosition(int num) {
        int y = 0, x = 0;
        if (num == 1 || num == 4 || num == 7) {
            if (num == 4) y = 1;
            else if (num == 7) y = 2;
            return new Point(y, x);
        } else if (num == 2 || num == 5 || num == 8 || num == 0) {
            x = 1;
            if (num == 5) y = 1;
            else if (num == 8) y = 2;
            else if (num == 0) y = 3;
            return new Point(y, x);
        } else if (num == 3 || num == 6 || num == 9) {
            x = 2;
            if (num == 6) y = 1;
            else if (num == 9) y = 2;
            return new Point(y, x);
        }
        return null;
    }

    public int getDistance(Point now, int y, int x) {
        int nowY = now.y;
        int nowX = now.x;
        return Math.abs(nowY - y) + Math.abs(nowX - x);
    }
}
