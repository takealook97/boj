import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer> dwarfList = new ArrayList<>();
	static int targetGap;
	static int DWARF_COUNT = 9;
	static int TARGET_SUM = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		for (int i = 0; i < DWARF_COUNT; i++) {
			int num = Integer.parseInt(br.readLine());
			dwarfList.add(num);
			sum += num;
		}
		targetGap = sum - TARGET_SUM;
		
		out:
		for (int i = 0; i < DWARF_COUNT - 1; i++) {
			for (int j = 1; j < DWARF_COUNT; j++) {
				if (dwarfList.get(i) + dwarfList.get(j) == targetGap) {
					dwarfList.remove(j);
					dwarfList.remove(i);
					break out;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int answer : dwarfList) {
			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}
}
