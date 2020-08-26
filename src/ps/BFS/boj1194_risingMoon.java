package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj1194_risingMoon {
	static class Item{
		Pair pos;
		int key, cnt;
		
		public Item(Pair pos, int key, int cnt) {
			super();
			this.pos = pos;
			this.key = key;
			this.cnt = cnt;
		}
	}
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		char[][] map = new char[n][m];
		boolean[][][] check = new boolean[n][m][1<<6];
		
		Pair start = null;
		for(int i=0;i<n;i++) {
			map[i] = sc.next().toCharArray();
			for(int j=0;j<map[i].length;j++) {
				if(map[i][j] == '0') {
					map[i][j] = '.';
					start = new Pair(i, j);
				}
			}
		}
		check[start.first][start.second][0] = true;
		Queue<Item> q = new LinkedList<>();
		q.add(new Item(start, 0, 0));
		
		int ans = -1;
		while(!q.isEmpty()) {
			Item front = q.poll();
			
			if(map[front.pos.first][front.pos.second] == '1') {
				ans = front.cnt;
				break;
			}
			
			for(int dir=0;dir<4;dir++) {
				int nRow = front.pos.first + delta[dir][0];
				int nCol = front.pos.second + delta[dir][1];
				
				if(0<=nRow && nRow<n && 0<=nCol && nCol<m) {
					if('A' <= map[nRow][nCol] && map[nRow][nCol] <= 'F' && !check[nRow][nCol][front.key] && (front.key & (1<<(map[nRow][nCol] - 'A'))) != 0) {
						check[nRow][nCol][front.key] = true;
						q.add(new Item(new Pair(nRow, nCol), front.key, front.cnt + 1));
					}else if('a' <= map[nRow][nCol] && map[nRow][nCol] <= 'f') {
						int nKey = (front.key | 1<<(map[nRow][nCol] - 'a'));
						if(!check[nRow][nCol][nKey]) {
							check[nRow][nCol][nKey] = true;
							q.add(new Item(new Pair(nRow, nCol), nKey, front.cnt + 1));
						}
					}else if((map[nRow][nCol] == '.' || map[nRow][nCol] == '1') && !check[nRow][nCol][front.key]) {
							check[nRow][nCol][front.key] = true;
							q.add(new Item(new Pair(nRow, nCol), front.key, front.cnt + 1));
					}
				}
			}
		}
		System.out.println(ans);
	}
}
