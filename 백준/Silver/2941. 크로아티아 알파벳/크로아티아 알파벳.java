import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        String[] change = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        for (int i = 0; i < change.length; i++) {
            if (word.contains(change[i])) {
                word = word.replace(change[i], "0");
            }
        }
        System.out.println(word.length());
    }
}