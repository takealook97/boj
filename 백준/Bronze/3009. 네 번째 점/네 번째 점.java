import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] a = new int[3];
        int[] b = new int[3];
        int A = 0;
        int B = 0;
        for (int i = 0; i < 3; i++) {
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }
        if (a[0] == a[1]) {
            A = a[2];
        } else if (a[0] == a[2]) {
            A = a[1];
        } else if (a[1] == a[2]) {
            A = a[0];
        }

        if (b[0] == b[1]) {
            B = b[2];
        } else if (b[0] == b[2]) {
            B = b[1];
        } else if (b[1] == b[2]) {
            B = b[0];
        }

        System.out.println(A + " " + B);
    }
}