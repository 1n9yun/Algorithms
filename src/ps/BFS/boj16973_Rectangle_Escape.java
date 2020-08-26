package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj16973_Rectangle_Escape {
	static int n,m,h,w;
	static int[][] partialSum;
	static boolean[][] check;
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class Item{
		Pair p;
		int count;
		public Item(Pair p, int count) {
			super();
			this.p = p;
			this.count = count;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		partialSum = new int[n+1][m+1];
		check = new boolean[n+1][m+1];
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				partialSum[i][j] = partialSum[i-1][j] + partialSum[i][j-1] - partialSum[i-1][j-1] + sc.nextInt();
			}
		}
		
		h = sc.nextInt();
		w = sc.nextInt();
		Pair start = new Pair(sc.nextInt(), sc.nextInt());
		Pair goal = new Pair(sc.nextInt(), sc.nextInt());
		
		Queue<Item> q = new LinkedList<>();
		
		check[start.first][start.second] = true;
		q.add(new Item(start, 0));
		
		while(!q.isEmpty()) {
			Item front = q.poll();
			
			if(front.p.first == goal.first && front.p.second == goal.second) {
				System.out.println(front.count);
				return;
			}
			
			for(int dir=0;dir<4;dir++) {
				int nRow = front.p.first + delta[dir][0];
				int nCol = front.p.second + delta[dir][1];
				
				if(!isOut(nRow, nCol) && !check[nRow][nCol] && getPartialSum(nRow, nCol, nRow + h - 1, nCol + w - 1) == 0) {
					check[nRow][nCol] = true;
					q.add(new Item(new Pair(nRow, nCol), front.count + 1));
				}
			}
		}
		
		System.out.println(-1);
	}
	
	static boolean isOut(int row, int col) {
		if(1<=row && row+h-1<=n && 1<=col && col+w-1<=m) return false;
		else return true;
	}
	
	static int getPartialSum(int r1, int c1, int r2, int c2) {
		return partialSum[r2][c2] - partialSum[r2][c1 - 1] - partialSum[r1 - 1][c2] + partialSum[r1 - 1][c1 - 1];
	}
}
