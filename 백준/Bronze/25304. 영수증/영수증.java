import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int X = sc.nextInt();
        int N = sc.nextInt();
        int sum = 0;

        for (int i=1; i<=N; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();

            sum = sum + (A*B);
        }

        if(sum==X){
            System.out.println("Yes");
        }

        else {
            System.out.println("No");
        }
    }
}
