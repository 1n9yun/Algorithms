package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea8382_ {
	static class Item{
		int row, col, cnt, prevDir;

		public Item(int row, int col, int cnt, int prevDir) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
			this.prevDir = prevDir;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			Item from = new Item(sc.nextInt() + 100, sc.nextInt() + 100, 0, 1000);
			Item to = new Item(sc.nextInt() + 100, sc.nextInt() + 100, 0, 0);
			
			int minRow, maxRow, minCol, maxCol;
			int rowSize, colSize;
			
			if(from.row > to.row) {
				minRow = to.row;
				maxRow = from.row;
			}else {
				minRow = from.row;
				maxRow = to.row;
			}
			
			if(from.col > to.col) {
				minCol = to.col;
				maxCol = from.col;
			}else {
				minCol = from.col;
				maxCol = to.col;
			}
			
			from.row -= minRow - 1;
			to.row -= minRow - 1;
			from.col -= minCol - 1;
			to.col -= minCol - 1;
			rowSize = maxRow - minRow + 1;
			colSize = maxCol - minCol + 1;
			
			boolean[][][] check = new boolean[rowSize+1][colSize+1][2];
			
			
			Queue<Item> q = new LinkedList<>();
			q.add(from);
			while(!q.isEmpty()) {
				Item front = q.poll();
				
				if(front.row == to.row && front.col == to.col) {
					System.out.println("#" + tc + " " + front.cnt);
					break;
				}
				for(int dir=0;dir<4;dir++) {
					if(front.prevDir < 2 && dir < 2) continue;
					else if(2<= front.prevDir && front.prevDir < 4 && dir > 1) break;
					
					int nRow = front.row + delta[dir][0];
					int nCol = front.col + delta[dir][1];
					
					if(0<=nRow && nRow<=rowSize && 0<=nCol && nCol<=colSize && !check[nRow][nCol][dir < 2 ? 0 : 1]) {
						check[nRow][nCol][dir < 2 ? 0 : 1] = true;
						q.add(new Item(nRow, nCol, front.cnt + 1, dir));
					}
				}
			}
		}
	}
}
