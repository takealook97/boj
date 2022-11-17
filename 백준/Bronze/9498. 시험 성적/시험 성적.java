import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a;
        a = scan.nextInt();

        if (0 <= a && a <= 100) {
            if(90 <= a) {
                System.out.println("A");
            }
            else if(80 <= a) {
                System.out.println("B");
            }
            else if(70 <= a) {
                System.out.println("C");
            }
            else if(60 <= a) {
                System.out.println("D");
            }
            else if(59 >= a) {
                System.out.println("F");
            }
        }


    }
}
