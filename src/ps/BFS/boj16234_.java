package ps.BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj16234_ {
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int l = sc.nextInt();
		int r = sc.nextInt();
		
		int[][] people = new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				people[i][j] = sc.nextInt();
			}
		}
		
		int ans = 0;
		
		while(true) {
			Queue<Pair> q = new LinkedList<>();
			Queue<Pair> union = new LinkedList<>();
			boolean[][] check = new boolean[n][n];
			int[][][] boundary = new int[n][n][4];
			
			boolean flag = false;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					for(int dir=0;dir<4;dir++) {
						int nRow = i + delta[dir][0];
						int nCol = j + delta[dir][1];
						
						if(0<=nRow && nRow<n && 0<=nCol && nCol<n) {
							int diff = Math.abs(people[nRow][nCol] - people[i][j]);
							if(l<=diff && diff<=r) {
								flag = true;
								boundary[i][j][dir] = 1;
							}
						}
					}
				}
			}
			if(flag) ans++;
			else break;
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(!check[i][j]) {
						int sum = 0;
						check[i][j] = true;
						q.add(new Pair(i, j));
						union.add(new Pair(i, j));
						
						while(!q.isEmpty()) {
							Pair front = q.poll();
							sum += people[front.first][front.second];
							
							for(int dir=0;dir<4;dir++) {
								int nRow = front.first + delta[dir][0];
								int nCol = front.second + delta[dir][1];
								
								if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol] && boundary[front.first][front.second][dir] == 1) {
									check[nRow][nCol] = true;
									q.add(new Pair(nRow, nCol));
									union.add(new Pair(nRow, nCol));
								}
							}
						}
						
						int avg = sum / union.size();
						while(!union.isEmpty()) {
							Pair p = union.poll();
							people[p.first][p.second] = avg;
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
