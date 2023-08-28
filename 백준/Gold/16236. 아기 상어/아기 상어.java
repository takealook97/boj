import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] board;
	static boolean[][] visit;
	static BabyShark babyShark;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	
	static class BabyShark {
		int y, x, size, gauge;

		public BabyShark(int y, int x, int size, int gauge) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.gauge = gauge;
		}
	}
	
	static class Fish implements Comparable<Fish>{
		int y, x, size, dist;
		

		public Fish(int y, int x, int size, int dist) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			if(this.dist == o.dist) {
				if(this.y == o.y) {
					return this.x - o.x;
				}
				return this.y - o.y;
			}
			return this.dist - o.dist;
		}
	}
	
	public static void main(String[] args) throws IOException{
	   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   N = Integer.parseInt(br.readLine());
	   board = new int[N][N];
	   for(int i = 0; i < N; i++) {
		   StringTokenizer st = new StringTokenizer(br.readLine());
		   for(int j = 0; j < N ; j++) {
			   board[i][j] = Integer.parseInt(st.nextToken());
			   if(board[i][j] == 9) {
				   babyShark = new BabyShark(i, j, 2, 0);
			   }
		   }
	   }
	   
	   int time = 0;
	   while(true) {
		   Fish prey = getPrey();
		   if(prey == null) {
			   break;//break
		   }
		   //먹이가 있다면
		   int nextGauge = babyShark.gauge + 1;
		   board[babyShark.y][babyShark.x] = 0;
		   if(nextGauge == babyShark.size) {
			   babyShark = new BabyShark(prey.y, prey.x, babyShark.size + 1, 0);
		   } else {
			   babyShark = new BabyShark(prey.y, prey.x, babyShark.size, babyShark.gauge + 1);
		   }
		   board[prey.y][prey.x] = 9;
		   time += prey.dist;
	   }
	   System.out.println(time);
	}
	
	static Fish getPrey() {
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		visit = new boolean[N][N];
		pq.add(new Fish(babyShark.y, babyShark.x, 0, 0));
		visit[babyShark.y][babyShark.x] = true;
		Fish prey = null;
		while(!pq.isEmpty()) {
			Fish now = pq.poll();
			if(isFish(now.y, now.x)) {
				if(board[now.y][now.x] < babyShark.size) {
					prey = now;
					break;
				} else if(board[now.y][now.x] > babyShark.size){
					continue;
				}
			}
			
			for(int i = 0; i < 4; i++) {
				int nextY = now.y + dy[i];
				int nextX = now.x + dx[i];
				if(isMovable(nextY, nextX)) {
					visit[nextY][nextX] = true;
					pq.add(new Fish(nextY, nextX, board[nextY][nextX], now.dist + 1));
				}
			}
		}
		return prey;
	}
	
	static boolean isFish(int y, int x) {
		return board[y][x] != 0 && board[y][x] != 9;
	}
	
	static boolean isMovable(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N && !visit[y][x] && board[y][x] <= babyShark.size;
	}
}
