import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String word = sc.nextLine();
        int[] ascii = new int[word.length()];

        for (int i = 0; i < word.length(); i++) {
            ascii[i] = word.charAt(i);
            if (ascii[i] > 90) {
                ascii[i] = ascii[i] - 32;
            }
        }
        int[] num = new int[26];//알파벳 별 개수

        for (int i = 0; i < 26; i++) {
            int []a = new int[26];
            for (int j = 0; j < word.length(); j++) {
                if (ascii[j] == 65 + i) {
                    num[i] ++;
                }
            }
        }
        int bignum = 0;
        int answer = 0;
        for (int i=0; i<26; i++){
            if(num[i]>bignum){
                bignum=num[i];
                answer = i;
            }
        }
        Arrays.sort(num);
        char alphabet = (char)(answer+65);
        if(num[25]==num[24]){
            System.out.println("?");
        }
        else {
            System.out.println(alphabet);
        }
    }
}