package ps;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj1857_ballerino {
	static class Item{
		int row, col, count;

		public Item(int row, int col, int count) {
			super();
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
	static class Pair{
		Item left, right;

		public Pair(Item left, Item right) {
			super();
			this.left = left;
			this.right = right;
		}
	}
	static int[][] delta = {
			{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{-1,-2},{1,-2}
	};
	static Item start, end;
	static int requiredCushion = 901;
	static long[][][] dp;
	static int[][] map;
	static int n,m;
	static boolean[][] check;
	static int[][] path;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];
		
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 3) start = new Item(i, j, 0);
				else if(map[i][j] == 4) end = new Item(i, j, 0);
			}
		}
		
		check = new boolean[n][m];
		Queue<Item> q = new LinkedList<>();
		check[start.row][start.col] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			Item front = q.poll();
			if(requiredCushion <= front.count) continue;
			if(map[front.row][front.col] == 4) {
				requiredCushion = front.count - 1;
				break;
			}
			for(int dir=0;dir<8;dir++) {
				int nRow = front.row + delta[dir][0];
				int nCol = front.col + delta[dir][1];
				
				if(0<=nRow && nRow<n && 0<=nCol && nCol<m && !check[nRow][nCol] && map[nRow][nCol] != 2) {
					check[nRow][nCol] = true;
					if(map[nRow][nCol] == 1) {
						findNextPosition(q, nRow, nCol, front.count);
					}
					else q.add(new Item(nRow, nCol, front.count + 1));
				}
			}
		}
		if(requiredCushion == 901) {
			System.out.println(-1);
			return;
		}else System.out.println(requiredCushion);
//		dp = new long[n][m][requiredCushion + 2];
		dp = new long[n][m][requiredCushion + 1];
		
//		path = new int[n][m];
//		for(int i=0;i<n;i++) {
//			path[i] = map[i].clone();
//		}
//		path[start.row][start.col] = 9;
		
//		check = new boolean[n][m];
//		check[start.row][start.col] = true;
//		System.out.println(back(start.row, start.col, 0));
		
		dp[start.row][start.col][0] = 1;
		for(int cushion = 0; cushion <= requiredCushion; cushion++) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j] == 0 && cushion > 0) {
						for(int dir=0;dir<8;dir++) {
							int nRow = i + delta[dir][0];
							int nCol = j + delta[dir][1];
							
							if(0<=nRow && nRow<n && 0<=nCol && nCol<m && map[nRow][nCol] != 2) {
								dp[i][j][cushion] += dp[nRow][nCol][cushion - 1]; 
							}
						}
					}
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j] == 1 || map[i][j] == 4) {
						for(int dir=0;dir<8;dir++) {
							int nRow = i + delta[dir][0];
							int nCol = j + delta[dir][1];
							
							if(0<=nRow && nRow<n && 0<=nCol && nCol<m && map[nRow][nCol] != 2) {
								dp[i][j][cushion] += dp[nRow][nCol][cushion];
							}
						}
					}
				}
			}
		}
		print();
		System.out.println(dp[end.row][end.col][requiredCushion]);
	}
	
	static long back(int row, int col, int count) {
		print();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(count > requiredCushion) return 0;
		if(count == requiredCushion && map[row][col] == 4) return dp[row][col][count] = 1;
		if(dp[row][col][count] != 0) return dp[row][col][count];
		
		long ret = 0;
		for(int dir=0;dir<8;dir++) {
			int nRow = row + delta[dir][0];
			int nCol = col + delta[dir][1];

			if(0<=nRow && nRow<n && 0<=nCol && nCol<m && map[nRow][nCol] != 2 && !check[nRow][nCol]) {
				int temp = path[nRow][nCol];
				path[nRow][nCol] = 9;
				
				check[nRow][nCol] = true;
				if(map[nRow][nCol] == 0) {
					ret += back(nRow, nCol, count + 1);
				}
				else {
					ret += back(nRow, nCol, count);
				}
				check[nRow][nCol] = false;
				path[nRow][nCol] = temp;
			}
		}
		return dp[row][col][count] = ret;
	}
	
//	static long findNextPositioninBack(int row, int col, int count) {
//		
//	}
	static void print() {
		for(int i=0;i<n;i++) {
			for(int c=0;c<=requiredCushion;c++) {
				if(c==0) {
					for(int j=0;j<m;j++) {
						System.out.printf("[%d] %4d, ", map[i][j], dp[i][j][0]);
					}
				}
				else {
					for(int j=0;j<m;j++) {
						System.out.printf("    %4d, ", dp[i][j][c]);
					}
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	
	
//	static void print() {
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<m;j++) {
//				System.out.print(path[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
	
	static void findNextPosition(Queue<Item> q, int row, int col, int count) {
		for(int dir=0;dir<8;dir++) {
			int nRow = row + delta[dir][0];
			int nCol = col + delta[dir][1];
			
			if(0<=nRow && nRow<n && 0<=nCol && nCol<m && !check[nRow][nCol] && map[nRow][nCol] != 2) {
				if(map[nRow][nCol] == 1) {
					check[nRow][nCol] = true;
					findNextPosition(q, nRow, nCol, count);
				}else q.add(new Item(nRow, nCol, count + 1));
			}
		}
	}
}
