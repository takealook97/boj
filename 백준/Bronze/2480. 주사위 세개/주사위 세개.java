import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int a,b,c;
        a = scan.nextInt();
        b = scan.nextInt();
        c = scan.nextInt();

        if(1<=a && a<=6 && 1<=b && b<=6 && 1<=c && c<=6){
            if(a==b && b==c) {
                System.out.println(10000+a*1000);
            }
            else if(a==b) {
                System.out.println(1000+a*100);
            }
            else if(b==c) {
                System.out.println(1000+b*100);
            }
            else if(c==a) {
                System.out.println(1000+c*100);
            }
            else if(a>b && a>c){
                System.out.println(a*100);
            }
            else if(b>a && b>c){
                System.out.println(b*100);
            }
            else if(c>a && c>b){
                System.out.println(c*100);
            }
        }

    }
}
