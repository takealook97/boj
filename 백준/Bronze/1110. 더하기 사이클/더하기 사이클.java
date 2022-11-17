import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int cycle=0;
        int M = N;

        while (true){
            cycle++;
            N = (N%10)*10+((N/10)+(N%10))%10;
            if(M==N){
                break;
            }
        }
        System.out.println(cycle);
    }
}
