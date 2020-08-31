package ps.CodingTest.BrandiContest_1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class brandi_02 {
	static class Pair{
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	static int[][] delta = {{1,0},{-1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[][] house = new int[n+1][n+1];
		int zero = 0;
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				house[i][j] = sc.nextInt();
				if(house[i][j] == 0) zero++;
			}
		}
		int ans = --zero;
		
		boolean[][] check = new boolean[n+1][n+1];
		check[1][1] = true;
		boolean[][] blockable = new boolean[n+1][n+1];
		blockable[n][n] = true;
		
		Queue<Pair> wq = new LinkedList<>();
		Queue<Pair> hq = new LinkedList<>(); 
		wq.add(new Pair(1, 1));
		hq.add(new Pair(n, n));
		
		while(!wq.isEmpty() || !hq.isEmpty()) {
			int wqSize = wq.size();
			for(int i=0;i<wqSize;i++) {
				Pair p = wq.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = p.r + delta[dir][0];
					int nCol = p.c + delta[dir][1];
					
					if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n && house[nRow][nCol] == 0 && !check[nRow][nCol]) {
						check[nRow][nCol] = true;
						ans--; // 막을 곳이 없을 때
						wq.add(new Pair(nRow, nCol));
					}
				}
			}
			
			int hqSize = hq.size();
			for(int i=0;i<hqSize;i++) {
				Pair p = hq.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = p.r + delta[dir][0];
					int nCol = p.c + delta[dir][1];
					
					if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n && house[nRow][nCol] == 0 && !check[nRow][nCol] && !blockable[nRow][nCol]) {
						blockable[nRow][nCol] = true;
						hq.add(new Pair(nRow, nCol));
					}
				}
			}
		}
		wq.clear();
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(!blockable[i][j] || (i == n && j == n)) continue;
				wq.add(new Pair(1,1));
				check = new boolean[n+1][n+1];
				check[1][1] = true;
				int cnt = 1;
				while(!wq.isEmpty()) {
					Pair p = wq.poll();
					
					for(int dir=0;dir<4;dir++) {
						int nRow = p.r + delta[dir][0];
						int nCol = p.c + delta[dir][1];
						if(nRow == i && nCol == j) continue;
						
						if(1<=nRow && nRow<=n && 1<=nCol && nCol<=n && house[nRow][nCol] == 0 && !check[nRow][nCol]) {
							check[nRow][nCol] = true;
							cnt++;
							wq.add(new Pair(nRow, nCol));
						}
					}
				}
				ans = Math.max(ans, zero-cnt);
			}
		}
		System.out.println(ans);
	}
}
