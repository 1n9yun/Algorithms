package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea1953_arrest {
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int hRow = sc.nextInt();
			int hCol = sc.nextInt();
			int l = sc.nextInt();
			
			int[][] map = new int[n][m];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					int type = sc.nextInt();
					
					if(type == 1) {
						map[i][j] = 1<<0 | 1<<1 | 1<<2 | 1<<3;
					}else if(type == 2) {
						map[i][j] = 1<<0 | 1<<2;
					}else if(type == 3) {
						map[i][j] = 1<<1 | 1<<3;
					}else if(type == 4) {
						map[i][j] = 1<<0 | 1<<1;
					}else if(type == 5) {
						map[i][j] = 1<<1 | 1<<2;
					}else if(type == 6) {
						map[i][j] = 1<<2 | 1<<3;
					}else if(type == 7) {
						map[i][j] = 1<<3 | 1<<0;
					}
				}
			}
			
			int ans = 1;
			boolean[][] check = new boolean[n][m];
			Queue<Pair> q = new LinkedList<>();
			check[hRow][hCol] = true;
			q.add(new Pair(hRow, hCol));
			
			while(!q.isEmpty()) {
				if(--l == 0) break;
				
				int size = q.size();
				for(int i=0;i<size;i++) {
					Pair front = q.poll();
					
					if(map[front.row][front.col] != 0) {
						for(int dir=0;dir<4;dir++) {
							int nRow = front.row + delta[dir][0];
							int nCol = front.col + delta[dir][1];
							
							if(0<=nRow && nRow<n && 0<=nCol && nCol<m && !check[nRow][nCol] && 
									(map[front.row][front.col] & (1<<dir)) != 0 && (map[nRow][nCol] & (1<<((4 + dir-2) % 4))) != 0) {
								check[nRow][nCol] = true;
								q.add(new Pair(nRow, nCol));
								ans++;
							}
						}
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
