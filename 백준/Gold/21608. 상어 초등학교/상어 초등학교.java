import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] studentOrder;
	static ArrayList<Integer>[] list;
	static int[][] board;
	static boolean[][] visit;
	static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
	static int answer = 0;

	static final int LIKE_COUNT = 4;

	static class Student implements Comparable<Student> {
		int y, x, likeCount, emptyCount;

		public Student(int y, int x, int likeCount, int emptyCount) {
			this.y = y;
			this.x = x;
			this.likeCount = likeCount;
			this.emptyCount = emptyCount;
		}

		@Override
		public int compareTo(Student o) {
			if (this.likeCount == o.likeCount) {
				if (this.emptyCount == o.emptyCount) {
					if (this.y == o.y) {
						return this.x - o.x;
					}
					return this.y - o.y;
				}
				return o.emptyCount - this.emptyCount;
			}
			return o.likeCount - this.likeCount;
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		studentOrder = new int[N * N];
		list = new ArrayList[N * N];
		board = new int[N][N];
		visit = new boolean[N][N];

		for (int i = 0; i < N * N; i++) {
			list[i] = new ArrayList<>();

		}
		for (int i = 0; i < N; i++) {
			Arrays.fill(board[i], -1);
		}

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int student = Integer.parseInt(st.nextToken()) - 1;
			studentOrder[i] = student;
			for (int j = 0; j < LIKE_COUNT; j++) {
				list[student].add(Integer.parseInt(st.nextToken()) - 1);
			}
		}
		for (int student : studentOrder) {
			setSeat(student);
		}
		getSum();
		System.out.println(answer);

	}

	static void setSeat(int studentNum) {
		PriorityQueue<Student> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visit[i][j]) {
					int likeCount = 0;
					int emptyCount = 0;
					for (int dir = 0; dir < 4; dir++) {
						int checkY = i + dy[dir];
						int checkX = j + dx[dir];
						if (isInRange(checkY, checkX) && visit[checkY][checkX]
								&& list[studentNum].contains(board[checkY][checkX])) {
							likeCount++;
						} else if (isInRange(checkY, checkX) && !visit[checkY][checkX]) {
							emptyCount++;
						}
					}
					pq.add(new Student(i, j, likeCount, emptyCount));
				}
			}
		}
		Student student = pq.poll();
		board[student.y][student.x] = studentNum;
		visit[student.y][student.x] = true;
	}

	static boolean isInRange(int nextY, int nextX) {
		return 0 <= nextY && nextY < N && 0 <= nextX && nextX < N;
	}

	static void getSum() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int count = 0;
				for (int dir = 0; dir < 4; dir++) {
					int checkY = i + dy[dir];
					int checkX = j + dx[dir];
					if (isInRange(checkY, checkX) && list[board[i][j]].contains(board[checkY][checkX])) {
						count++;
					}
				}
				if (count > 0) {
					answer += (int) Math.pow(10, count - 1);
				}
			}
		}
	}
}
