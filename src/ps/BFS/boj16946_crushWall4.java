package ps.BFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class boj16946_crushWall4 {
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
		int m = sc.nextInt();
		
		char[][] map = new char[n][m];
		int[][] mark = new int[n][m];
		
		for(int i=0;i<n;i++) {
			map[i] = sc.next().toCharArray();
		}
		
		boolean[][] check = new boolean[n][m];
		int[] cnts = new int[n*m + 1];
		
		int numbering = 1;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j] == '0' && !check[i][j]) {
					Queue<Pair> q = new LinkedList<>();
					q.add(new Pair(i, j));
					check[i][j] = true;
					mark[i][j] = numbering;
					
					int cnt = 1;
					while(!q.isEmpty()) {
						Pair front = q.poll();
						
						for(int dir=0;dir<4;dir++) {
							int nRow = front.first + delta[dir][0];
							int nCol = front.second + delta[dir][1];
							
							if(0<=nRow && nRow<n && 0<=nCol && nCol<m && !check[nRow][nCol] && map[nRow][nCol] == '0') {
								check[nRow][nCol] = true;
								mark[nRow][nCol] = numbering;
								cnt++;
								q.add(new Pair(nRow, nCol));
							}
						}
					}
					
					cnts[numbering++] = cnt;
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j] == '1') {
					Set<Integer> added = new HashSet<>();
					
					int cnt = 1;
					for(int dir=0;dir<4;dir++) {
						int nRow = i + delta[dir][0];
						int nCol = j + delta[dir][1];
						
						if(0<=nRow && nRow<n && 0<=nCol && nCol<m && map[nRow][nCol] == '0') {
							if(added.add(mark[nRow][nCol])) {
								cnt += cnts[mark[nRow][nCol]];
							}
						}
					}
					
					map[i][j] = (char)((cnt % 10) + '0');
				}
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
