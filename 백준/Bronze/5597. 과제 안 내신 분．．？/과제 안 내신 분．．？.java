import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[31];

        for (int i = 1; i < 29; i++) {
            int commit = sc.nextInt();
            array[commit] = 1;
        }

        for (int j = 1; j < array.length; j++) {
            if (array[j] != 1) {
                System.out.println(j);
            }
        }
    }
}