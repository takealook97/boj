import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Long> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		if (N > 1022) {
			System.out.println(-1);
		} else {
			for (int i = 0; i < 10; i++) {
				find(i, 1);
			}
			Collections.sort(list);
			System.out.println(list.get(N));
		}
	}

	static void find(long num, int length) {
		if (length > 10) {
			return;
		}

		list.add(num);
		for (int i = 0; i < num % 10; i++) {
			find(num * 10 + i, length + 1);
		}
	}
}
