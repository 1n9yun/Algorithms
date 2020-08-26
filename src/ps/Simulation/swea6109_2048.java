package ps.Simulation;

import java.util.Arrays;
import java.util.Scanner;

public class swea6109_2048 {
	static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			String dir = sc.next();
			
			int[][] board = new int[n][n];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					board[i][j] = sc.nextInt();
				}
			}
			
			if(dir.equals("up")) updown(board, 1);
			else if(dir.equals("down")) updown(board, -1);
			else if(dir.equals("left")) leftright(board, 1);
			else if(dir.equals("right")) leftright(board, -1);
			
			System.out.println("#" + tc);
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(board[i][j] + " ");
				}
				System.out.println();
			}
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
