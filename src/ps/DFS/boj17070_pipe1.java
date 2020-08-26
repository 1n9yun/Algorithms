package ps.DFS;

import java.util.Scanner;

public class boj17070_pipe1 {
	//	가로 세로 대각
	//	{다음 방향, delta row, delta col}
	static int n;
	static int[][][] delta = {
			{{0,0,1},{2,1,1}},
			{{1,1,0},{2,1,1}},
			{{0,0,1},{1,1,0},{2,1,1}}
	};
	static int[][] house;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		house = new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				house[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(dfs(0, 1, 2));
	}
	
	static int dfs(int dir, int row, int col) {
		if(row == n && col == n) return 1;
		
		int ret = 0;
		for(int i=0;i<delta[dir].length;i++) {
			int nRow = row + delta[dir][i][1];
			int nCol = col + delta[dir][i][2];
			
			if(1<=nRow && nRow<=n && 1<=nCol && nCol <=n) {
				if(delta[dir][i][0] == 2) {
					if(house[nRow-1][nCol] == 1 || house[nRow][nCol-1] == 1) break;
				}
				if(house[nRow][nCol] != 1) {
					ret += dfs(delta[dir][i][0], nRow, nCol);
				}
			}
		}
		return ret;
	}
}