import java.io.*;
import java.util.Scanner;

public class Main {
	static int N;
	static String[] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new String[N];
		arr[0] = "  *  ";
		arr[1] = " * * ";
		arr[2] = "*****";
		for (int i = 1; 3 * Math.pow(2, i) <= N; i++) {
			makeStars(i);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(arr[i]).append("\n");
		}
		System.out.print(sb);
	}

	static void makeStars(int i) {
		int bottom = (int)(3 * Math.pow(2, i));
		int mid = bottom / 2;
		for (int j = mid; j < bottom; j++) {
			arr[j] = arr[j - mid] + " " + arr[j - mid];
		}

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < mid; j++) {
			sb.append(" ");
		}
		String blank = sb.toString();

		for (int j = 0; j < mid; j++)
			arr[j] = blank + arr[j] + blank;
	}
}
