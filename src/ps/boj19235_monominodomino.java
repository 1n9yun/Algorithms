package ps;

import java.util.Scanner;

public class boj19235_monominodomino {
	static int n;
	static int[][] delta = {
			{0,0},
			{0,0},
			{1,0},
			{0,1}
	};
	class Board{
		boolean[][] board;
		final int FILED_LEN = 4;
		final int SPECIAL_LEN = 2;
		
		Board(){
			board = new boolean[10][10];
		}
		
		public void put(int t, int r, int c) {
			goGreen(t, r, c);
			goBlue(t, r, c);
		}
		
		private void goGreen(int t, int r, int c) {
			int resR = r;
			int resC = c;
			
//			아래로 내려가야돼
			while(resR + 1 < board.length && resR + 1 + delta[t][0] < board.length) {
				if(!board[resR + 1][resC] && !board[resR + 1 + delta[t][0]][resC + delta[t][1]]) {
					resR++;
				}else break;
			}
			
			board[resR][resC] = true;
			board[resR + delta[t][0]][resC + delta[t][1]] = true;
		}
		
		private void goBlue(int t, int r, int c) {
			int resR = r;
			int resC = c;
			
//			오른쪽으로 가야돼
			while(resC + 1 < board.length && resC + 1 + delta[t][1] < board.length) {
				if(!board[resR][resC + 1] && !board[resR + delta[t][0]][resC + 1 + delta[t][1]]) {
					resC++;
				}else break;
			}
			
			board[resR][resC] = true;
			board[resR + delta[t][0]][resC + delta[t][1]] = true;
		}
		
		private void checkLine() {
			
		}
		
		private void checkSpecial() {
			
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			int t = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			
		}
	}
}
