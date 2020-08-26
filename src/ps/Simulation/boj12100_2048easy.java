package ps.Simulation;

import java.util.Arrays;
import java.util.Scanner;

public class boj12100_2048easy {
	static int n;
	static int[][] board;
	static int[][] temp;
	static int ans = -1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		board = new int[n][n];
		temp = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				board[i][j] = sc.nextInt();
			}
		}
		perm(0, new int[5]);
		
		System.out.println(ans);
	}
	
	static void perm(int cnt, int[] result) {
		if(cnt == result.length) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					temp[i][j] = board[i][j];
				}
			}
			
			for(int i=0;i<result.length;i++) {
				switch(result[i]) {
				case 0:
					updown(temp, 1);
					break;
				case 1:
					updown(temp, -1);
					break;
				case 2:
					leftright(temp, 1);
					break;
				case 3:
					leftright(temp, -1);
					break;
				}
			}
			
			for(int[] sub : temp) {
				for(int n : sub) {
					ans = Math.max(ans, n);
				}
			}
			
			return;
		}
		for(int i=0;i<4;i++) {
			result[cnt] = i;
			perm(cnt + 1, result);
		}
	}
	static void updown(int[][] board, int dir) {
		for(int j=0;j<n;j++) {
			int bottom = dir == 1 ? 0 : n - 1;
			int finder = bottom + dir;
			
			while(0<= finder && finder < n && 0<= bottom && bottom < n) {
				while(0<= finder && finder < n && board[finder][j] == 0) finder += dir;
				if(finder == n || finder == -1) break;
				
				if(board[bottom][j] == 0) {
					board[bottom][j] = board[finder][j];
					board[finder][j] = 0;
				}else {
					if(board[bottom][j] == board[finder][j]) {
						board[bottom][j] *= 2;
						board[finder][j] = 0;
					}else {
						board[bottom + dir][j] = board[finder][j];
						if(finder != bottom + dir) board[finder][j] = 0;
						else finder += dir;
					}
					bottom += dir;
				}
			}
		}
	}
	static void leftright(int[][] board, int dir) {
		for(int j=0;j<n;j++) {
			int bottom = dir == 1 ? 0 : n - 1;
			int finder = bottom + dir;
			
			while(0<= finder && finder < n) {
				while(0<= finder && finder < n && board[j][finder] == 0) finder += dir;
				if(finder == n || finder == -1) break;
				
				if(board[j][bottom] == 0) {
					board[j][bottom] = board[j][finder];
					board[j][finder] = 0;
				}else {
					if(board[j][bottom] == board[j][finder]) {
						board[j][bottom] *= 2;
						board[j][finder] = 0;
					}else {
						board[j][bottom + dir] = board[j][finder];
						if(finder != bottom + dir) board[j][finder] = 0;
						else finder += dir;
					}
					bottom += dir;
				}
			}
		}
	}
}
