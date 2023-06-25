import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.next();
		}
		StringBuilder sb = new StringBuilder();
		boolean check = true;
		for (int i = 0; i < arr[0].length(); i++) {
			char alphabet = arr[0].charAt(i);
			check = true;
			for (int j = 1; j < N; j++) {
				if (alphabet != arr[j].charAt(i)) {
					check = false;
					break;
				}
			}
			if (check) {
				sb.append(alphabet);
			} else
				sb.append("?");
		}
		System.out.println(sb);
	}
}
