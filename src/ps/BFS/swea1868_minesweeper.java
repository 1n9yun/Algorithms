package ps.BFS;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class swea1868_minesweeper {
	static int[][] delta = {{-1,-1},{-1,0},{-1,1},{0,-1,},{0,1},{1,-1},{1,0},{1,1}};
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			
			char[][] map = new char[n][n];
			boolean[][] check = new boolean[n][n];
			int[][] mines = new int[n][n];
			for(int i=0;i<n;i++) {
				map[i] = sc.next().toCharArray();
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] == '*') mines[i][j] = 2;
					else for(int dir=0;dir<8;dir++) {
						int nRow = i + delta[dir][0];
						int nCol = j + delta[dir][1];
						
						if(0<=nRow && nRow<n && 0<=nCol && nCol<n) {
							if(map[nRow][nCol] == '*') {
								mines[i][j] = 1;
								break;
							}
						}
					}
				}
			}
			int ans = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(mines[i][j] == 2) continue;
					if(!check[i][j] && mines[i][j] == 0) {
						Deque<Pair> q = new ArrayDeque<>();
						q.add(new Pair(i, j));
						check[i][j] = true;
						while(!q.isEmpty()) {
							Pair front = q.poll();
							for(int dir=0;dir<8;dir++) {
								int nRow = front.row + delta[dir][0];
								int nCol = front.col + delta[dir][1];
								if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol]) {
									check[nRow][nCol] = true;
									if(mines[nRow][nCol] == 0) q.add(new Pair(nRow, nCol));
								}
							}
						}
						ans++;
					}
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(!check[i][j] && mines[i][j] == 1) ans++;
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
