import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] array = new int[8];
        String result = "";

        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }
        sc.close();

        for (int i = 0; i < array.length- 1; i++) {
            if (array[i] == array[i + 1] - 1) {
                result = "ascending";
            } else if (array[i] == array[i + 1] + 1) {
                result = "descending";
            } else {
                result = "mixed";
                break;
            }
        }
        System.out.println(result);
    }
}
