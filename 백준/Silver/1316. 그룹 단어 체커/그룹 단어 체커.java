import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int count = N;

        for (int i = 0; i < N; i++) {
            String word = sc.next();
            boolean [] check = new boolean[26];

            for (int j = 0; j < word.length() - 1; j++) {
                if (word.charAt(j) != word.charAt(j + 1)) {
                    if (check[word.charAt(j + 1) - 'a'] == true) {
                        count--;
                        break;
                    }
                }
                check[word.charAt(j) - 'a'] = true;
            }
        }
        System.out.println(count);
    }
}