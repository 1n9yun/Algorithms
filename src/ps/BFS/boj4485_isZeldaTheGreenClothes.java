package ps.BFS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 굳이 다익스트라로 안해도 풀리네 BFS 메모이제이션 연습
public class boj4485_isZeldaTheGreenClothes {
	static int[][] cave;
	static int n;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static int[][] check;
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int TC = 1;
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			
			cave = new int[n][n];
			check = new int[n][n];
			for(int[] sub : check) Arrays.fill(sub, Integer.MAX_VALUE - 10);
			
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			check[0][0] = cave[0][0];
			Queue<Pair> q = new LinkedList<>();
			q.add(new Pair(0, 0));
			
			while(!q.isEmpty()) {
				Pair front = q.poll();
				
				for(int dir=0;dir<4;dir++) {
					int nRow = front.row + delta[dir][0];
					int nCol = front.col + delta[dir][1];
					
					if(0<=nRow && nRow<n && 0<=nCol && nCol<n && check[nRow][nCol] > (check[front.row][front.col] + cave[nRow][nCol])) {
						check[nRow][nCol] = check[front.row][front.col] + cave[nRow][nCol];
						q.add(new Pair(nRow, nCol));
					}
				}
			}
			
//			dfs(0, 0);
			System.out.println("Problem " + (TC++) + ": " + check[n-1][n-1]);
		}
	}
	
//	호출 개많아 당연히 뻗지 
	static void dfs(int row, int col) {
		if(row == n-1 && col == n-1) return;
		
		for(int dir=0;dir<4;dir++) {
			int nRow = row + delta[dir][0];
			int nCol = col + delta[dir][1];
			
			if(0<=nRow && nRow<n && 0<=nCol && nCol<n && check[nRow][nCol] > check[row][col] + cave[nRow][nCol]) {
				check[nRow][nCol] = check[row][col] + cave[nRow][nCol];
				dfs(nRow, nCol);
			}
		}
	}
}
