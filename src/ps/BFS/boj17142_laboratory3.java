package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj17142_laboratory3 {
	static class Virus{
		int row, col;

		public Virus(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int n,m;
	static int[][] map;
	static int blank = 0;
	static Virus[] virus;
	static int vcnt = 0;
	static int[][] delta = {{1,0},{-1,0},{0,-1},{0,1}};
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][n];
		virus = new Virus[n*n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 0) blank++;
				else if(map[i][j] == 2) {
					virus[vcnt++] = new Virus(i, j);
				}
			}
		}
		
		comb(0, 0, new int[m], new boolean[vcnt]);
		System.out.println(ans == 987654321 ? -1 : ans);
	}
	
	static void comb(int idx, int cnt, int[] result, boolean[] check) {
		if(cnt == result.length) {
			int tBlank = blank;
			boolean[][] visited = new boolean[n][n];
			
			Queue<Virus> q = new LinkedList<>();
			for(int i=0;i<result.length;i++) {
				q.add(virus[result[i]]);
				visited[virus[result[i]].row][virus[result[i]].col] = true;
			}
			
			int tAns = 0;
			while(!q.isEmpty()) {
				int size = q.size();
				
				if(tAns >= ans) return;
				if(tBlank == 0) {
					break;
				}
				
				for(int i=0;i<size;i++) {
					Virus front = q.poll();
					
					for(int dir=0;dir<4;dir++) {
						int nRow = front.row + delta[dir][0];
						int nCol = front.col + delta[dir][1];
						
						if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !visited[nRow][nCol]) {
							visited[nRow][nCol] = true;
							if(map[nRow][nCol] == 0) {
								tBlank--;
								q.add(new Virus(nRow, nCol));
							}else if(map[nRow][nCol] == 2) {
								q.add(new Virus(nRow, nCol));
							}
						}
					}
				}
				tAns++;
			}
			
			if(tBlank == 0)
				ans = Math.min(ans, tAns);
			
			return;
		}
		
		for(int i=idx;i<vcnt;i++) {
			if(check[i]) continue;
			check[i] = true;
			result[cnt] = i;
			comb(i + 1, cnt + 1, result, check);
			check[i] = false;
		}
	}
}
