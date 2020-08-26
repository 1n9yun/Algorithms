package ps.BackTracking;

import java.util.ArrayList;
import java.util.Scanner;

public class swea1949_hikingTrail {
	static int n, k;
	static int[][] map;
	static boolean[][] check;
	static int ans;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			ans = -1;
			n = sc.nextInt();
			k = sc.nextInt();
			
			map = new int[n][n];
			
			int sHeight = -1;
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					map[i][j] = sc.nextInt();
					sHeight = Math.max(sHeight, map[i][j]);
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j] == sHeight) {
						check = new boolean[n][n];
						check[i][j] = true;
						ans = Math.max(ans, dfs(i, j, map[i][j], false));
					}
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int dfs(int row, int col, int prevh, boolean cut) {
		int res = 0;
		for(int dir=0;dir<4;dir++) {
			int nRow = row + delta[dir][0];
			int nCol = col + delta[dir][1];
			
			if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol]) {
				boolean just = map[nRow][nCol] < prevh;
				boolean cutmove = !cut && (map[nRow][nCol] - k) < prevh;
				if(just || cutmove) {
					check[nRow][nCol] = true;
					if(just) {
						res = Math.max(res, dfs(nRow, nCol, map[nRow][nCol], cut));
					}else if(cutmove) {
						res = Math.max(res, dfs(nRow, nCol, map[row][col] - 1, true));
					}
					check[nRow][nCol] = false;
				}
			}
		}
		return res + 1;
	}
}
