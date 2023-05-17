import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long min = scanner.nextLong();
        long max = scanner.nextLong();
        int count = (int) (max - min + 1);
        boolean[] result = new boolean[count];
        for (long i = 2; i * i <= max; i++) {
            long square = i * i;
            long start = (min / square) * square;
            if (start < min) start += square;
            for (long j = start; j <= max; j += square) {
                if (!result[(int) (j - min)]) {
                    result[(int) (j - min)] = true;
                    count--;
                }
            }
        }
        System.out.println(count);
    }
}
