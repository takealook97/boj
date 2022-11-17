import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int i = 0;
        int sum = 0;

        while(i<=a){
            sum = sum+i;
            i++;
        }
        System.out.println(sum);
    }
}
