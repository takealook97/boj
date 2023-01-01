import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String jh = sc.next();
        String dt = sc.next();
        int jhCount = 0;
        int dtCount = 0;
        for (int i = 0; i < jh.length(); i++) {
            if (jh.charAt(i) == 'a') {
                jhCount++;
            }
        }
        for (int i = 0; i < dt.length(); i++) {
            if (dt.charAt(i) == 'a') {
                dtCount++;
            }
        }
        if (jhCount < dtCount) {
            System.out.println("no");
        } else System.out.println("go");
    }
}
