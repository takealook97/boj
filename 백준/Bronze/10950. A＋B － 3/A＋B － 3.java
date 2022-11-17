import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int T = scan.nextInt();

        for(int i=1; i<=T; i++){
            int A = scan.nextInt();
            int B = scan.nextInt();

            System.out.println(A+B);
        }
        scan.close();
    }
}