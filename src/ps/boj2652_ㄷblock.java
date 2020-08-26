package ps;

import java.util.ArrayList;
import java.util.Scanner;

public class boj2652_ㄷblock {
	static class Pair{
		int left;
		int right;
		public Pair(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	static class Block{
		ArrayList<ArrayList<Pair>> rows;
//		상하좌우
//		상하 ? 좌우 ? : 양 끝 점에서 내려가면서 계속 같으면 상하
//		상 ? 하 ? : 맨 위 행, 아래 행 길이 차이
//		좌 ? 우 ? : 각 row당 양 끝 점에서 내려가면서 먼저 커지는 쪽이 뚫림
		int dir;
	}
	static int[][] delta = {{0,1},{1,0}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int u = sc.nextInt();
		int v = sc.nextInt();
		int w = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		
		int[][] board = new int[n][n];
		boolean[][] check = new boolean[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!check[i][j]) {
					check[i][j] = true;
					
				}
			}
		}
	}
}
