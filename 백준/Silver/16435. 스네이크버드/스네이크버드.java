import java.io.*;
import java.util.*;

public class Main {
	static int N, L;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		while (N-- > 0) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		int fruit = 0;
		while (!pq.isEmpty()) {
			fruit = pq.poll();
			if (L >= fruit) {
				L++;
			} else {	
				break;
			}
		}
		System.out.println(L);
	}
}
