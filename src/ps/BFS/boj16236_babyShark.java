package ps.BFS;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class boj16236_babyShark {
	static class Fish{
		int row, col, size, yummy, time;

		public Fish(int row, int col, int size, int yummy, int time) {
			super();
			this.row = row;
			this.col = col;
			this.size = size;
			this.yummy = yummy;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Fish [row=" + row + ", col=" + col + ", size=" + size + ", yummy=" + yummy + ", time=" + time + "]";
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] map = new int[n][n];
		
		Fish shark = null;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 9) {
					shark = new Fish(i, j, 2, 0, 0);
					map[i][j] = 0;
				}
			}
		}
		
		while(true) {
			boolean[][] check = new boolean[n][n];
			check[shark.row][shark.col] = true;
			
			Queue<Fish> q = new LinkedList<>();
			q.add(new Fish(shark.row, shark.col, shark.size, -1, shark.time));
			
			PriorityQueue<Fish> pq = new PriorityQueue<>((o1, o2) -> {
				if(o1.time > o2.time) return 1;
				else if(o1.time == o2.time) {
					if(o1.row > o2.row) return 1;
					else if(o1.row == o2.row) {
						if(o1.col > o2.col) return 1;
					}
				}
				return -1;
			});
			
			while(!q.isEmpty()) {
				Fish front = q.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = front.row + delta[dir][0];
					int nCol = front.col + delta[dir][1];
					
					if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol]) {
						if(map[nRow][nCol] == 0) {
							check[nRow][nCol] = true;
							q.add(new Fish(nRow, nCol, front.size, -1, front.time + 1));
						}else if(map[nRow][nCol] < front.size) {
//							먹이 후보
							check[nRow][nCol] = true;
							pq.add(new Fish(nRow, nCol, -1, -1, front.time + 1));
						}else if(map[nRow][nCol] == front.size) {
							check[nRow][nCol] = true;
							q.add(new Fish(nRow, nCol, front.size, -1, front.time + 1));
						}
					}
				}
			}
			
			if(pq.isEmpty()) break;
			
			Fish feed = pq.poll();
			if(shark.yummy + 1 == shark.size) {
				shark = new Fish(feed.row, feed.col, shark.size + 1, 0, feed.time);
			}else shark = new Fish(feed.row, feed.col, shark.size, shark.yummy + 1, feed.time);
			
			map[feed.row][feed.col] = 0;
		}
		System.out.println(shark.time);
	}
}
