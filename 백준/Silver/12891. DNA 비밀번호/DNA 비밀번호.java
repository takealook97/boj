import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String arr = br.readLine();
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int count = 0;
        int head = 0;
        int tail = P - 1;

        HashMap<Character, Integer> alphabet = new HashMap<>();
        alphabet.put('A', 0);
        alphabet.put('C', 0);
        alphabet.put('G', 0);
        alphabet.put('T', 0);

        for (int j = head; j <= tail; j++) {//첫 윈도우 등록
            if (alphabet.containsKey(arr.charAt(j))) {
                alphabet.put(arr.charAt(j), alphabet.get(arr.charAt(j)) + 1);
            }
        }
        if (alphabet.get('A') >= A && alphabet.get('C') >= C && alphabet.get('G') >= G && alphabet.get('T') >= T) {
            count++;
        }

        for (int i = 1; i <= S - P; i++) {//슬라이딩
            if (alphabet.containsKey(arr.charAt(head))) {//옆칸으로 이동 후 헤드 이동하면서 이전 헤드 맵에 포함시 삭제
                alphabet.put(arr.charAt(head), alphabet.get(arr.charAt(head)) - 1);
            }
            head++;
            tail++;
            if (alphabet.containsKey(arr.charAt(tail))) {//추가된 인덱스가 맵에 포함될 시 추가
                alphabet.put(arr.charAt(tail), alphabet.get(arr.charAt(tail)) + 1);
            }
            if (alphabet.get('A') >= A && alphabet.get('C') >= C && alphabet.get('G') >= G && alphabet.get('T') >= T) {
                count++;
            }
        }
        System.out.println(count);
    }
}