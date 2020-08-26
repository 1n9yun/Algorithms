package ps.Toss_200801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Toss_6 {
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(";");
		int n = input.length;
		int m = 0;
		
		char[][] map = new char[n][];
		Pair start = null;
		
		for(int i=0;i<n;i++) {
			String[] s = input[i].split(" ");
			m = s.length;
			
			map[i] = new char[m];
			for(int j=0;j<m;j++) {
				map[i][j] = s[j].charAt(0);
				if(map[i][j] == '1' && start == null) {
					start = new Pair(i,j);
				}
			}
		}
		
		boolean[][] check = new boolean[n][m];
		
		Queue<Pair> q = new LinkedList<>();
		q.add(start);
		check[start.row][start.col] = true;
		
		int ans = 0;
		
		while(!q.isEmpty()) {
			Pair front = q.poll();
			System.out.println();
			System.out.println(front.row + " " + front.col);
			
			
			for(int dir=0;dir<4;dir++) {
				int nRow = front.row + delta[dir][0];
				int nCol = front.col + delta[dir][1];
				
				if(0<=nRow && nRow<n && 0<=nCol && nCol<m && !check[nRow][nCol]) {
					if(map[nRow][nCol] == '0') {
						System.out.println(nRow + " " + nCol);
						ans++;
					}
					else {
						check[nRow][nCol] = true;
						q.add(new Pair(nRow, nCol));
					}
				}
			}
		}
		
		System.out.println(ans);
	}
}
