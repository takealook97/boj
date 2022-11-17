import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A,B,C;
        A = scan.nextInt();
        B = scan.nextInt();
        C = scan.nextInt();

        if(0<=A && A<=23 && 0<=B && B<=59 && 0<=C && C<=1000){
            if(A*60+B+C >= 1440) {
                System.out.println((A*60+B+C-1440)/60);
                System.out.println((A*60+B+C-1440)%60);
            }
            else {
                System.out.println((A*60+B+C)/60);
                System.out.println((A*60+B+C)%60);
            }
        }
    }
}
