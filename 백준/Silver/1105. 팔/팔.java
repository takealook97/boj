import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String L = sc.next();
        String R = sc.next();
        int result = 0;
        if (L.length() != R.length()) System.out.println(result);
        else {
            int point = 0;
            while (point < L.length() && L.charAt(point) == R.charAt(point)) {
                if(L.charAt(point) == '8'){
                    result++;
                }
                point++;
            }
            System.out.println(result);
        }
    }
}