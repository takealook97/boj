import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int [] array = new int [9];
        int max = array[0];
        int N = 0;

        for (int i=0; i<9; i++){
            array[i] = sc.nextInt();
            if(array[i]>max){
                max = array[i];
                N=i+1;
            }
        }
        System.out.println(max);
        System.out.println(N);
    }
}
