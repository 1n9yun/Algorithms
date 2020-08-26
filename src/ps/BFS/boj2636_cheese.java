package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj2636_cheese {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static int[][] map;
	static int cnt;
	static boolean[][] check;
	static int n,m;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<Pair> bye;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		check = new boolean[n][m];
		bye = new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j] == 1) cnt++;
			}
		}
		
		bye.add(new Pair(0, 0));
		int ans = 0;
		int ans2 = 0;
		while(cnt != 0) {
			ans++;
			ans2 = cnt;
			bfs();
//			for(int[] sub : map) System.out.println(Arrays.toString(sub));
//			System.out.println();
//			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		System.out.println(ans);
		System.out.println(ans2);
	}
	
	static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		while(!bye.isEmpty()) {
			Pair byebye = bye.poll();
			
			check[byebye.first][byebye.second] = true;
			q.add(byebye);
		}
		
		while(!q.isEmpty()) {
			Pair front = q.poll();
			
			for(int dir=0;dir<4;dir++) {
				int nRow = front.first + delta[dir][0];
				int nCol = front.second + delta[dir][1];
				
				if(0<=nRow && nRow<n && 0<=nCol && nCol<m && !check[nRow][nCol]) {
					if(map[nRow][nCol] == 1) {
						check[nRow][nCol] = true;
						map[nRow][nCol] = 0;
						
						bye.add(new Pair(nRow, nCol));
						cnt--;
					}else {
						check[nRow][nCol] = true;
						q.add(new Pair(nRow, nCol));
					}
				}
			}
		}
	}
}
