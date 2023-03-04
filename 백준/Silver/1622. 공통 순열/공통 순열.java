import java.io.*;
import java.util.*;

public class Main {
	static int N, M, W;

	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			char[] a=sc.nextLine().toCharArray();
			char[] b=sc.nextLine().toCharArray();
			Map<Character, Integer> L = new HashMap<>();
			Map<Character, Integer> R = new HashMap<>();
			for(char c: a)
				L.put(c, L.getOrDefault(c, 0)+1);
			for(char c: b)
				R.put(c, R.getOrDefault(c, 0)+1);
			List<Character> list=new ArrayList<>();
			for(char k: L.keySet()) {
				if(R.containsKey(k)) {
					int m = Math.min(L.get(k), R.get(k));
					while(m-->0)
						list.add(k);
				}
			}
			Collections.sort(list);
			String s="";
			for(char c: list) {
				s+=c;
			}
			sb.append(s+"\n");
		}
		System.out.println(sb);
	}

	static int stoi(String s) {
		return Integer.valueOf(s);
	}
}