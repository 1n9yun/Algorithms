package ps.DFS;

import java.util.Scanner;

public class bj3109_bakery {
	static boolean[][] check;
	static int r,c;
	static int[][] delta = {{-1,1},{0,1},{1,1}};
	static char[][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); 
		
		r = sc.nextInt();
		c = sc.nextInt();
		sc.nextLine();
		map = new char[r][c];
		check = new boolean[r][c];
		
		for(int i=0;i<r;i++) {
			char[] input = sc.next().toCharArray();
			for(int j=0;j<c;j++) {
				map[i][j] = input[j];
			}
		}
		
		int ans = 0;
		for(int i=0;i<r;i++) {
			check[i][0] = true;
			if(dfs(i, 0)) ans++;
		}
		System.out.println(ans);
	}
	
	
	static boolean dfs(int row, int col) {
		if(col == c-1) return true;
		
		for(int dir=0;dir<3;dir++) {
			int nRow = row + delta[dir][0];
			int nCol = col + delta[dir][1];
			
			if(0<=nRow && nRow<r && 0<=nCol && nCol<c && !check[nRow][nCol] && map[nRow][nCol] == '.') {
				if(dir == 2) check[nRow-1][nCol] = true;
				check[nRow][nCol] = true;
				if(dfs(nRow, nCol)) return true;
			}
		}
		return false;
	}
}
