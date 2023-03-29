import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static ArrayList<Integer> answer = new ArrayList<>();
    static ArrayList<Integer> given = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            answer.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            given.add(Integer.parseInt(st.nextToken()));
        }
        given.addAll(given);
        if (checkContain()) System.out.println("good puzzle");
        else System.out.println("bad puzzle");

    }

    static int getStartIndex() {
        return given.indexOf(answer.get(0));
    }

    static int getEndIndex() {
        int endNum = answer.get(N - 1);
        for (int i = given.size() - 1; i >= 0; i--) {
            if (given.get(i) == endNum) {
                return i;
            }
        }
        return 0;
    }

    static boolean checkContain() {
        int start = getStartIndex();
        int end = getEndIndex();
        int index = 0;
        for (int i = start; i <= end; i++) {
            if (given.get(i).equals(answer.get(0))) {
                index = i;
                break;
            }
        }
        for (int i = index; i < index + N; i++) {
            if (!given.get(i).equals(answer.get(i - index))) {
                Collections.reverse(given);
                return checkReverse();
            }
        }
        return true;
    }

    static boolean checkReverse() {
        int start = getStartIndex();
        int end = getEndIndex();
        int index = 0;
        for (int i = start; i <= end; i++) {
            if (given.get(i).equals(answer.get(0))) {
                index = i;
                break;
            }
        }
        for (int i = index; i < index + N; i++) {
            if (!given.get(i).equals(answer.get(i - index))) {
                return false;
            }
        }
        return true;
    }
}
