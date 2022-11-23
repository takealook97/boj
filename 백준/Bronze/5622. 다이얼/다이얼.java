import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String Alphabet = sc.nextLine();
        int[] cnt = new int[27];
        int[] input = new int[15];

        for (int i = 0; i < 5; i++) {//알파벳 별 추가값 설정(cnt0~25)
            cnt[0] = 2;
            for (int j = 1 + (3 * i); j < 4 + (3 * i); j++) {
                cnt[j] = 3 + i;
            }
        }
        for (int i = 16; i < 20; i++)
            cnt[i] = 8;
        for (int i = 20; i < 23; i++)
            cnt[i] = 9;
        for (int i = 23; i < 27; i++)
            cnt[i] = 10;

        int answer = 0;
        for (int i = 0; i < Alphabet.length(); i++) {
            input[i] = Alphabet.charAt(i) - 64;//입력 알파벳 to 아스키코드
            answer = answer + cnt[input[i]];
        }
        System.out.println(answer);
    }
}