import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int[] range = new int[X+1];
        int a = 0;
        int b = 0;

        for (int i = 1; i <= X; i++) {
            range[0] = 0;
            range[i] = range[i - 1] + i;
            if (range[i - 1] < X && X <= range[i]) {
                a=X-range[i-1];
                b=i+1-a;
                if(i%2==0) {
                    System.out.println(a + "/" + b);
                    break;
                }
                else{
                    System.out.println(b + "/" + a);
                    break;
                }
            }
        }
    }
}