package ps.BFS;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class swea1907_sand_castle_boj10711 {
	static char[][] map;
	static int[][] counts;
	static int h,w;
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int[][] delta = {
			{-1,-1},{-1,0},{-1,1},
			{0,-1},{0,1},
			{1,-1},{1,0},{1,1}
	};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			h = sc.nextInt();
			w = sc.nextInt();

			map = new char[h][w];
			counts = new int[h][w];
			for(int[] sub : counts) Arrays.fill(sub, -1);
			for(int i=0;i<h;i++) {
				String input = sc.next();
				for(int j=0;j<w;j++) {
					map[i][j] = input.charAt(j);
				}
			}
			
			int ans = 0;
			Deque<Pair> q = new ArrayDeque<>();
			
			for(int i=1;i<h-1;i++) {
				for(int j=1;j<w-1;j++) {
					if(map[i][j] == '.') continue;
					int cnt = 0;
					
					for(int dir=0;dir<8;dir++) {
						int nRow = i + delta[dir][0];
						int nCol = j + delta[dir][1];
						if(map[nRow][nCol] == '.') cnt++;
					}
					counts[i][j] = cnt;
					if(cnt >= (map[i][j] - '0')) {
						counts[i][j] = -1;
						q.add(new Pair(i, j));
					}
				}
			}
			while(!q.isEmpty()) {
				int size = q.size();
				ans++;
				
				for(int i=0;i<size;i++) {
					Pair p = q.poll();
					counts[p.row][p.col] = -1;
					for(int dir=0;dir<8;dir++) {
						int nRow = p.row + delta[dir][0];
						int nCol = p.col + delta[dir][1];
						if(counts[nRow][nCol] != -1) {
							if(counts[nRow][nCol] + 1 >= (map[nRow][nCol] - '0')) {
								counts[nRow][nCol] = -1;
								q.add(new Pair(nRow, nCol));
							}else counts[nRow][nCol]++;
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
}
