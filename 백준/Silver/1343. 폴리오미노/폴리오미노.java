import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] board = input.split("\\.");
        String[] result = new String[board.length];
        boolean nullCheck = false;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != null) {
                nullCheck = true;
                break;
            }
        }
        if (!nullCheck) {
            System.out.println(input);
            System.exit(0);
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i].length() % 2 == 1) {
                System.out.println(-1);
                System.exit(0);
            } else {
                if (board[i].length() >= 4) {
                    int countA = board[i].length() / 4;
                    boolean checkB = (board[i].length() % 4 == 2);
                    //count 만큼 AAAA
                    result[i] = "";
                    for (int j = 0; j < countA; j++) {
                        result[i] += "AAAA";
                    }
                    if (checkB) {
                        result[i] += "BB";
                    }
                } else if (board[i].length() >= 2) {
                    result[i] = "BB";
                } else {
                    result[i] = "";
                }
            }
        }
        if (result.length == 1) {
            System.out.print(result[0]);
        } else {
            int count = 0;
            for (int i = input.length() - 1; i >= 0; i--) {
                if (input.charAt(i) == '.') count++;
                else break;
            }
            String add = "";
            if (count > 0) {
                for (int i = 0; i < count; i++) {
                    add += ".";
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(result[0]);
            for (int i = 1; i < result.length; i++) {
                sb.append(".").append(result[i]);
            }
            sb.append(add);
//            if(input.get)
            System.out.println(sb);
        }
    }
}