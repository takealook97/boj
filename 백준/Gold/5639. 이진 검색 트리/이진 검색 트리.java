import java.io.*;

public class Main {
    static StringBuilder answer = new StringBuilder();

    static class Node {
        int num;
        Node left, right;

        Node(int num) {
            this.num = num;
        }

        void insert(int num) {
            if (num < this.num) {
                if (this.left == null) {
                    this.left = new Node(num);
                } else {
                    this.left.insert(num);
                }
            } else {
                if (this.right == null) {
                    this.right = new Node(num);
                } else {
                    this.right.insert(num);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node rootNode = new Node(Integer.parseInt(br.readLine()));
        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.isEmpty()) {
                break;
            }
            rootNode.insert(Integer.parseInt(input));
        }

        postOrder(rootNode);

        System.out.print(answer);
    }

    static void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        answer.append(node.num).append("\n");
    }
}
