import java.util.Scanner;

public class Main {
    static String word;
    static char[] arr;
    static int result = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        word = sc.nextLine();
        arr = word.toCharArray();
        if (isSame()) {
            System.out.println(-1);
            System.exit(0);
        }

        out:
        for (int i = word.length() - 1; i >= 0; i--) {
            int left = 0;
            int right = left + i;
            while (right <= word.length() - 1) {
                if (isPalindrome(left, right)) {
                    left++;
                    right++;
                } else {
                    result = right - left + 1;
                    break out;
                }
            }
        }
        System.out.println(result);
    }

    static boolean isSame() {
        char c = word.charAt(0);
        for (char i : word.toCharArray()) {
            if (c != i) return false;
        }
        return true;
    }

    static boolean isPalindrome(int left, int right) {
        while (left <= right) {
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}