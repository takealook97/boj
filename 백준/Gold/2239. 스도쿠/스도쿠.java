import java.io.*;
import java.util.*;

public class Main {
	static int[][] board;
	static int blankCount = 0;
	static ArrayList<Point> blankList = new ArrayList<>();
	static final int BLANK = 0, BOARD_LENGTH = 9;
	
	static class Point {
		int y, x;
		
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[BOARD_LENGTH][BOARD_LENGTH];
		
		for(int i = 0; i < BOARD_LENGTH; i++) {
			String line = br.readLine();
			for(int j = 0; j < BOARD_LENGTH; j++) {
				board[i][j] = line.charAt(j) - '0';
				if(board[i][j] == BLANK) {
					blankCount++;
					blankList.add(new Point(i, j));
				}
			}
		}
        
		dfs(0);
	}
	
	static void dfs(int idx) {
		if(idx == blankCount) {
			printBoard();
			System.exit(0);
		}
		
		Point now = blankList.get(idx);
		ArrayList<Integer> possibleList = getPossibleNums(now.y, now.x);
		if(possibleList.isEmpty()) {
			return;
		}
		for(int num : possibleList) {
			board[now.y][now.x] = num;
			dfs(idx + 1);
			board[now.y][now.x] = 0;
		}
		
	}
	
	static ArrayList<Integer> getPossibleNums(int y, int x) {
		// 초기 설정
		ArrayList<Integer> possibleList = new ArrayList<>();
		for(int i = 1; i <= 9; i++) {
			possibleList.add(i);
		}
		
		// 가로 체크
		for(int i = 0; i < BOARD_LENGTH; i++) {
			if(possibleList.contains(board[y][i])) {
				possibleList.remove(Integer.valueOf(board[y][i]));
			}
		}
		
		// 세로 체크
		for(int i = 0; i < BOARD_LENGTH; i++) {
			if(possibleList.contains(board[i][x])) {
				possibleList.remove(Integer.valueOf(board[i][x]));
			}
		}
		
		// 사각 체크
		int startY = y / 3 * 3;
		int startX = x / 3 * 3;
		for(int i = startY; i < startY + 3; i++) {
			for(int j = startX; j < startX + 3; j++) {
				if(possibleList.contains(board[i][j])) {
					possibleList.remove(Integer.valueOf(board[i][j]));
				}
			}
		}
		
		return possibleList;
	}
	
	static void printBoard() {// 스도쿠 출력
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < BOARD_LENGTH; i++) {
			for(int j = 0; j < BOARD_LENGTH; j++) {
				sb.append(board[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
