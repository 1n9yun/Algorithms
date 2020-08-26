package ps.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// 시간 메모리 너무하다야
public class bj2146_bridge1 {
	static class Pair{
		int first, second, cnt;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
		public Pair(int first, int second, int cnt) {
			super();
			this.first = first;
			this.second = second;
			this.cnt = cnt;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] map = new int[n][n];
		boolean[][] check = new boolean[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int islandsIdx = 1;
		ArrayList<ArrayList<Pair>> islands = new ArrayList<>();
		islands.add(new ArrayList<>());
		Queue<Pair> q = new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!check[i][j] && map[i][j] == 1) {
					check[i][j] = true;
					map[i][j] = islandsIdx;
					
					islands.add(new ArrayList<Pair>());
					
					q.add(new Pair(i,j));
					islands.get(islands.size() - 1).add(new Pair(i,j));
					
					while(!q.isEmpty()) {
						Pair front = q.poll();
						for(int dir=0;dir<4;dir++) {
							int nRow = front.first + delta[dir][0];
							int nCol = front.second + delta[dir][1];
							
							if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol] && map[nRow][nCol] == 1) {
								check[nRow][nCol] = true;
								map[nRow][nCol] = islandsIdx;
								
								islands.get(islands.size() - 1).add(new Pair(nRow, nCol));
								q.add(new Pair(nRow, nCol));
							}
						}
					}
					
					islandsIdx++;
				}
			}
		}

		int ans = 987654321;
		
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.printf("%2d ", map[i][j]);
//			}
//			System.out.println();
//		}
		
		for(int i=1;i<islands.size();i++) {
			ArrayList<Pair> thisIslands = islands.get(i);
			for(Pair p : thisIslands) {
				for(int dir=0;dir<4;dir++) {
					int nRow = p.first + delta[dir][0];
					int nCol = p.second + delta[dir][1];
					
					if(0<=nRow && nRow<n && 0<=nCol && nCol<n && map[nRow][nCol] == 0) {
						q.add(p);
					}
				}
			}
			
			for(boolean[] sub : check) Arrays.fill(sub, false);
			
			while(!q.isEmpty()) {
				Pair front = q.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = front.first + delta[dir][0];
					int nCol = front.second + delta[dir][1];
					
					if(0<=nRow && nRow<n && 0<=nCol && nCol<n && !check[nRow][nCol]) {
						if(map[nRow][nCol] == 0) {
							check[nRow][nCol] = true;
							q.add(new Pair(nRow, nCol, front.cnt + 1));
						}else if(map[nRow][nCol] != i) {
//							System.out.println(i + " to " + map[nRow][nCol] + " : " + front.cnt);
							ans = Math.min(ans, front.cnt);
						}
					}
				}
			}
			
		}
		System.out.println(ans);
		

	}
}
