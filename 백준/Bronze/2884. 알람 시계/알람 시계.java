import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int H,M;
        H = scan.nextInt();
        M = scan.nextInt();

        if(0<=H && H<=23 && 0<=M && M<=59){
            if(H==0 && M<45){
                System.out.println("23");
                System.out.println(M+15);
            }
            else if(H!=0 && M<45){
                System.out.println(H-1);
                System.out.println(M+15);
            }
            else {
                System.out.println(H);
                System.out.println(M-45);
            }
        }
    }
}

