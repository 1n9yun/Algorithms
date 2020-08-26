package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj2206_crushWall {
	static class Pair{
		int left, right, cnt;
		boolean crushed;

		public Pair(int left, int right, int cnt, boolean crushed) {
			super();
			this.left = left;
			this.right = right;
			this.cnt = cnt;
			this.crushed = crushed;
		}
	}
//	static int[][] memo;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static int n,m;
	static char[][] map;
//	벽을 최대한 안부순 채로 가는게 이득일 수 있음
	static boolean[][][] check;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new char[n+1][m+1];
//		memo = new int[n+1][m+1];
		check = new boolean[n+1][m+1][2];
		for(int i=1;i<=n;i++) {
			char[] input = sc.next().toCharArray();
			for(int j=1;j<=m;j++) {
				map[i][j] = input[j-1];
			}
		}
		
		check[1][1][0] = true;
		int ans = 987654321;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(1, 1, 1, false));
		while(!q.isEmpty()) {
			Pair front = q.poll();
			
			if(front.left == n && front.right == m) {
				ans = Math.min(ans, front.cnt);
				break;
			}
			for(int dir=0;dir<4;dir++) {
				int nRow = front.left + delta[dir][0];
				int nCol = front.right + delta[dir][1];
				
				if(1<=nRow && nRow<=n && 1<=nCol && nCol<=m) {
					if(map[nRow][nCol] == '0') {
						if(!check[nRow][nCol][front.crushed ? 1 : 0]) {
							check[nRow][nCol][front.crushed ? 1 : 0] = true;
							q.add(new Pair(nRow, nCol, front.cnt + 1, front.crushed));
						}
					}else if(map[nRow][nCol] == '1' && !front.crushed) {
						check[nRow][nCol][1] = true;
						q.add(new Pair(nRow, nCol, front.cnt + 1, true));
					}
				}
			}
		}
		System.out.println(ans == 987654321 ? -1 : ans);
//		int res = dfs(1,1,false);
//		System.out.println(res == 987654322 ? -1 : res);
	}
	
//	static int dfs(int row, int col, boolean crushed) {
//		System.out.println(row + " " + col);
//		if(row == n && col == m) return memo[row][col] = 1;
//		if(memo[row][col] != 0) return memo[row][col];
//		
//		int ret = 987654321;
//		for(int dir=0;dir<4;dir++) {
//			int nRow = row + delta[dir][0];
//			int nCol = col + delta[dir][1];
//			
//			if(1<=nRow && nRow<=n && 1<=nCol && nCol<=m && !check[nRow][nCol]) {
//				if(map[nRow][nCol] == '0') {
//					// 그냥 이동
//					check[nRow][nCol] = true;
//					ret = Math.min(ret, dfs(nRow, nCol, crushed));
//					check[nRow][nCol] = false;
//				}else if(map[nRow][nCol] == '1') {
//					// 부숴버리기
//					if(!crushed) {
//						ret = Math.min(ret,  dfs(nRow, nCol, true));
//					}
//				}
//			}
//		}
//		return memo[row][col] = ret + 1;
//	}
}