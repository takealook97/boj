import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String [] ox = new String[N];

        for(int i=0; i<N; i++){
            ox[i]= sc.next();
        }

        for(int j=0; j<ox.length; j++) {
            int count = 0;
            int sum = 0;
            for (int k=0; k<ox[j].length(); k++){
                if (ox[j].charAt(k)=='O'){
                    count++;
                }
                else {
                    count=0;
                }
                sum += count;
            }
            System.out.println(sum);
        }

    }
}