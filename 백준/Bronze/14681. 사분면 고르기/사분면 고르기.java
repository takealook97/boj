import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int x,y;
        x = scan.nextInt();
        y = scan.nextInt();

        if(-1000<=x && x<=1000 && x!=0 && -1000<=y && y<=1000 && y!=0){
            if(x>0 && y>0) {
                System.out.println("1");
            }
            if(x<0 && y>0) {
                System.out.println("2");
            }
            if(x<0 && y<0) {
                System.out.println("3");
            }
            if(x>0 && y<0) {
                System.out.println("4");
            }
        }
    }
}
