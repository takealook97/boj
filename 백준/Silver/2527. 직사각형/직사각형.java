import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] A;
	static int[] B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 0; tc < 4; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = new int[4];
			B = new int[4];
			for (int i = 0; i < 4; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < 4; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			if (isNone()) {
				System.out.println("d");
			} else if (isPoint()) {
				System.out.println("c");
			} else if (isLine()) {
				System.out.println("b");
			} else {
				System.out.println("a");
			}
		}
	}

	static boolean isNone() {
		return (A[2] < B[0] || A[3] < B[1] || B[2] < A[0] || B[3] < A[1]);
	}

	static boolean isPoint() {
		return ((A[0] == B[2] && A[1] == B[3]) || (A[0] == B[2] && A[3] == B[1]) || (A[2] == B[0] && A[3] == B[1]) || (
			A[2] == B[0] && A[1] == B[3]));
	}

	static boolean isLine() {
		return (A[2] == B[0] || A[3] == B[1] || B[2] == A[0] || A[1] == B[3]);
	}
}
