import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.nextLine();
        int[] result = new int[26];
        int[] word = new int[S.length()];

        for (int i = 0; i < 26; i++) {
            result[i] = -1;
        }
        for (int i = 0; i < S.length(); i++) {
            word[i] = S.charAt(i) - 97;
        }
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (word[i] == j && result[j] == -1) {
                    result[j] = i;
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
