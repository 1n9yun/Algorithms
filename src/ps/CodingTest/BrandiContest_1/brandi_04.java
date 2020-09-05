package ps.CodingTest.BrandiContest_1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class brandi_04 {
	static class Item{
		int r, c;
		int d;
		public Item(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int d = sc.nextInt();
		
		int[][] field = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				field[i][j] = sc.nextInt();
			}
		}
		
		int ans = 0;
		Queue<Item> q = new LinkedList<>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(field[i][j] == 0) {
					boolean[][] check = new boolean[n][n];
					check[i][j] = true;
					
					q.add(new Item(i, j, 0));
					int cnt = 0;
					while(!q.isEmpty()) {
						Item item = q.poll();
						cnt++;
						
						if(item.d == d) continue;
						for(int dir=0;dir<4;dir++) {
							int nRow = item.r + delta[dir][0];
							int nCol = item.c + delta[dir][1];
							
							if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol] && field[nRow][nCol] == 0) {
								check[nRow][nCol] = true;
								q.add(new Item(nRow, nCol, item.d + 1));
							}
						}
					}
					
					ans = Math.max(ans, cnt);
				}
			}
		}
		
		
		System.out.println(ans);
		
	}
}
