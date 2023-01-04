import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = new String[3];
        long[] num = new long[3];
        for (int i = 0; i < 3; i++) {
            input[i] = sc.nextLine();
            switch (input[i]) {
                case "black":
                    num[i] = 0;
                    break;
                case "brown":
                    num[i] = 1;
                    break;
                case "red":
                    num[i] = 2;
                    break;
                case "orange":
                    num[i] = 3;
                    break;
                case "yellow":
                    num[i] = 4;
                    break;
                case "green":
                    num[i] = 5;
                    break;
                case "blue":
                    num[i] = 6;
                    break;
                case "violet":
                    num[i] = 7;
                    break;
                case "grey":
                    num[i] = 8;
                    break;
                case "white":
                    num[i] = 9;
                    break;
            }
        }
        long result = (num[0] * 10 + num[1]) * (long) Math.pow(10, num[2]);
        System.out.println(result);
    }
}
