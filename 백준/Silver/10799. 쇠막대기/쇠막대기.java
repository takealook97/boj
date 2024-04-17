import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int N = line.length(), sum = 0, answer = 0;
		
		for (int i = 0; i < N; i++) {
			if (line.charAt(i) == '(') {
				sum++;
			} else {
				sum--;
				if (line.charAt(i - 1) == '(') {
					answer += sum;
				} else {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}
}
