import java.io.*;
import java.util.*;

public class Main {
	static class Room implements Comparable<Room> {
		int start, end;

		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Room o) {
			if (this.end == o.end) {
				return this.start - o.start;
			}
			return this.end - o.end;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Room> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		
		int curEnd = list.get(0).end;
		int answer = 1;

		for (int i = 1; i < N; i++) {
			Room nextRoom = list.get(i);
			if (curEnd <= nextRoom.start) {
				curEnd = nextRoom.end;
				answer++;
			}
		}
		System.out.println(answer);
	}
}
