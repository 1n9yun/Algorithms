package ps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class _02 {
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static class Item{
		int r, c, d;

		public Item(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][n];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		d -= map[0][0];
		int ans = -1;
		
		Queue<Item> q = new ArrayDeque<Item>(1000001);
		q.add(new Item(0, 0, d));
		int[][] check = new int[n][n];
		check[0][0] = d;
		
		while(!q.isEmpty()) {
			Item front = q.poll();
			
			if(ans >= front.d || check[front.r][front.c] > front.d) continue;
			
			if(front.r == n-1 && front.c == n-1) {
				ans = Math.max(ans, front.d);
			}
			
			for(int dir=0;dir<delta.length;dir++) {
				int nRow = front.r + delta[dir][0];
				int nCol = front.c + delta[dir][1];
				
				if(0<=nRow && nRow<n && 0<=nCol && nCol<n) {
					int nD = front.d - map[nRow][nCol];
					if(nD <= 0 || check[nRow][nCol] >= nD) continue;
					
					check[nRow][nCol] = nD;
					q.add(new Item(nRow, nCol, nD));
				}
			}
		}
		
		System.out.println(ans);
		
	}
}
