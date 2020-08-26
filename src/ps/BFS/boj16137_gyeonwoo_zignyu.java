package ps.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
// 문제 설명 그지같음
//5 10 
//1 1 2 3 1 
//1 1 0 0 1 
//13 0 0 0 1 
//1 1 1 1 1 
//1 1 1 1 1
//이런 경우도 있음
public class boj16137_gyeonwoo_zignyu{
	static class Item{
		Pair pos;
		int cnt;
		int crossedCnt;
		public Item(Pair pos, int cnt, int crossedCnt) {
			super();
			this.pos = pos;
			this.cnt = cnt;
			this.crossedCnt = crossedCnt;
		}
	}
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] map;
	static int n,m;
	static boolean[][] check;
	static Pair[] bridgables;
	static int bridgableSize = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][n];
		check = new boolean[n][n];
		bridgables = new Pair[n*n];
		bridgables[bridgableSize++] = new Pair(0, 0);
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		mapInit();
		
		int ans = 987654321;
		for(int i=0;i<bridgableSize;i++) {
			int tempAns = 987654321;
			if(i != 0) map[bridgables[i].row][bridgables[i].col] = m;
			
			Queue<Item> q = new LinkedList<>();
			q.add(new Item(new Pair(0, 0), 0, -1));
			check[0][0] = true;
			while(!q.isEmpty()) {
				Item front = q.poll();
				if(map[front.pos.row][front.pos.col] > 1 && front.crossedCnt < front.cnt) {
					check[front.pos.row][front.pos.col] = false;
					continue;
				}
				if(front.pos.row == n-1 && front.pos.col == n-1) {
					tempAns = front.cnt;
					break;
				}
				boolean movable = false;
				for(int dir=0;dir<4;dir++) {
					int nRow = front.pos.row + delta[dir][0];
					int nCol = front.pos.col + delta[dir][1];
					
					if(!isOut(nRow, nCol) && !check[nRow][nCol] && map[nRow][nCol] >= 1) {
						movable = true;
//						건너기
						if(map[nRow][nCol] > 1 && (front.cnt + 1) % map[nRow][nCol] == 0 && front.crossedCnt != front.cnt) {
							check[nRow][nCol] = true;
							q.add(new Item(new Pair(nRow, nCol), front.cnt + 1, front.cnt + 1));
						}
						if(map[nRow][nCol] == 1) {
							check[nRow][nCol] = true;
							q.add(new Item(new Pair(nRow, nCol), front.cnt + 1, front.crossedCnt));
						}
					}
				}
				if(movable)
					q.add(new Item(front.pos, front.cnt + 1, front.crossedCnt));
			}
			map[bridgables[i].row][bridgables[i].col] = 0;
			for(boolean[] sub : check) Arrays.fill(sub, false);
			
			ans = Math.min(ans, tempAns);
		}
		System.out.println(ans);
	}
	
	static void mapInit() {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j] == 0) {
					boolean flag = true;
					for(int ud=0;ud<2;ud++) {
						for(int lr=2;lr<4;lr++) {
							int lRow = i + delta[ud][0];
							int lCol = j + delta[ud][1];
							int rRow = i + delta[lr][0];
							int rCol = j + delta[lr][1];
							if(!isOut(lRow, lCol) && (map[lRow][lCol] == 0 || map[lRow][lCol] == -1) && !isOut(rRow, rCol) && (map[rRow][rCol] == 0 || map[rRow][rCol] == -1)) {
								map[i][j] = -1;
								flag = false;
								ud = 2;
								lr = 4;
							}
						}
					}
					if(flag) bridgables[bridgableSize++] = new Pair(i, j); 
				}
			}
		}
	}
	
	static boolean isOut(int row, int col) {
		return !(0<=row && row<n && 0<=col && col<n);
	}
}
