import java.util.Stack;

class Solution {
    static Node[] arr;
    static int now;
    static Stack<Integer> stack = new Stack<>();
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int index;
        int prev;
        int next;
        boolean isDeleted;

        public Node(int index, int prev, int next) {
            this.index = index;
            this.prev = prev;
            this.next = next;
            this.isDeleted = false;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        arr = new Node[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Node(i, i - 1, i + 1);
        }
        now = k;
        arr[n - 1].next = -1;

        for (String command : cmd) {
            String[] order = command.split(" ");
            switch (order[0]) {
                case "U" -> goUp(Integer.parseInt(order[1]));
                case "D" -> goDown(Integer.parseInt(order[1]));
                case "C" -> delete();
                case "Z" -> restore();
            }
        }

        while (arr[now].prev != -1) {
            now = arr[now].prev;
        }

        for (int i = 0; i < n; i++) {
            if (arr[i].isDeleted) sb.append("X");
            else sb.append("O");
        }
        return sb.toString();
    }

    public static void goUp(int dist) {
        for (int i = 0; i < dist; i++) {
            now = arr[now].prev;
        }
    }

    public static void goDown(int dist) {
        for (int i = 0; i < dist; i++) {
            now = arr[now].next;
        }
    }

    public static void delete() {
        Node current = arr[now];
        current.isDeleted = true;
        stack.push(now);

        if (current.prev != -1) {
            arr[current.prev].next = current.next;
        }
        if (current.next != -1) {
            arr[current.next].prev = current.prev;
        }

        if (current.next != -1) {
            now = current.next;
        } else {
            now = current.prev;
        }
    }

    public static void restore() {
        int restore = stack.pop();
        arr[restore].isDeleted = false;

        if (arr[restore].prev != -1) {
            arr[arr[restore].prev].next = restore;
        }
        if (arr[restore].next != -1) {
            arr[arr[restore].next].prev = restore;
        }
    }
}
