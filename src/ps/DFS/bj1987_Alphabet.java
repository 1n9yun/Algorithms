package ps.DFS;

import java.util.Scanner;

public class bj1987_Alphabet {
	static class Item {
		int left, right, check, cnt = 0;
		
		public Item(int left, int right, int check, int cnt) {
			super();
			this.left = left;
			this.right = right;
			this.check = check;
			this.cnt = cnt;
		}
	}
	static int r,c;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static char[][] board;
	static int ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		r = sc.nextInt();
		c = sc.nextInt();

		board = new char[r][c];

		for (int i = 0; i < r; i++) {
			char[] input = sc.next().toCharArray();
			for (int j = 0; j < c; j++) {
				board[i][j] = input[j];
			}
		}
		
		dfs(0, 0, 1, 1<<(board[0][0]-'A'));
		
		System.out.println(ans);
	}
	// BFS시 메모리 초과 ; 깊이는 DFS로 하자;;
	static void dfs(int row, int col, int cnt, int check) {
		ans = Math.max(ans, cnt);
		if(ans == 26) return;
		for(int i=0;i<4;i++) {
			int nRow = row + delta[i][0];
			int nCol = col + delta[i][1];
			
			if(0<=nRow && nRow<r && 0<=nCol && nCol<c && (check & 1<<(board[nRow][nCol] - 'A')) == 0) {
				dfs(nRow, nCol, cnt + 1, check | 1<<(board[nRow][nCol] - 'A'));
			}
		}
	}
}
