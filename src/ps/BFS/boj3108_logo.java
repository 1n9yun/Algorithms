package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj3108_logo {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int size = 1001;
		boolean[][][] map = new boolean[size][size][5];
		
		for(int i=0;i<n;i++) {
			int r1 = sc.nextInt() + 500;
			int c1 = sc.nextInt() + 500;
			int r2 = sc.nextInt() + 500;
			int c2 = sc.nextInt() + 500;
			
			int lr = r1 < r2 ? r1 : r2;
			int lc = c1 < c2 ? c1 : c2;
			int rr = r1 < r2 ? r2 : r1;
			int rc = c1 < c2 ? c2 : c1;
			
			for(int j=lc;j<rc;j++) {
				map[lr][j][4] = true;
				map[lr][j][3] = true;
				map[lr][j+1][2] = true;
			}
			for(int j=lr;j<rr;j++) {
				map[j][rc][4] = true;
				map[j][rc][1] = true;
				map[j+1][rc][0] = true;
			}
			for(int j=rc;j>lc;j--) {
				map[rr][j][4] = true;
				map[rr][j][2] = true;
				map[rr][j-1][3] = true;
			}
			for(int j=rr;j>lr;j--) {
				map[j][lc][4] = true;
				map[j][lc][0] = true;
				map[j-1][lc][1] = true;
			}
		}
		boolean[][] check = new boolean[size][size];
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(500, 500));
		check[500][500] = true;
		while(!q.isEmpty()) {
			Pair front = q.poll();
			
			for(int dir=0;dir<4;dir++) {
				int nRow = front.first + delta[dir][0];
				int nCol = front.second + delta[dir][1];
				
				if(0<=nRow && nRow<size && 0<=nCol && nCol<size && map[front.first][front.second][dir] && !check[nRow][nCol]) {
					check[nRow][nCol] = true;
					q.add(new Pair(nRow, nCol));
				}
			}
		}
		
		int ans = 0;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(!check[i][j] && map[i][j][4]) {
					ans++;
					q.add(new Pair(i, j));
					check[i][j] = true;
					
					while(!q.isEmpty()) {
						Pair front = q.poll();
						
						for(int dir=0;dir<4;dir++) {
							int nRow = front.first + delta[dir][0];
							int nCol = front.second + delta[dir][1];
							
							if(0<=nRow && nRow<size && 0<=nCol && nCol<size && map[front.first][front.second][dir] && !check[nRow][nCol]) {
								check[nRow][nCol] = true;
								q.add(new Pair(nRow, nCol));
							}
						}
					}
				}
			}
		}
		System.out.println(ans);
	}
}
