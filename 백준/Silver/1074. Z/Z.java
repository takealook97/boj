import java.util.Scanner;

public class Main {
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        makeArray(r, c, (int) Math.pow(2, N));
        System.out.println(count);
    }

    static void makeArray(int r, int c, int size) {
        int partition = size * size / 4;
        if (size == 1) return;
        if (r < size / 2 && c < size / 2) {
            makeArray(r, c, size / 2);
        } else if (r < size / 2 && c >= size / 2) {
            count += partition;
            makeArray(r, c - size / 2, size / 2);
        } else if (r >= size / 2 && c < size / 2) {
            count += partition * 2;
            makeArray(r - size / 2, c, size / 2);
        } else {
            count += partition * 3;
            makeArray(r - size / 2, c - size / 2, size / 2);
        }
    }
}