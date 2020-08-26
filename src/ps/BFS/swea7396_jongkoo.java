
package ps.BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class swea7396_jongkoo {
	static int[][] delta = {{0,1},{1,0}};
	static class Item{
		int row, col;
		char alphabet;
		public Item(int row, int col, char alphabet) {
			super();
			this.row = row;
			this.col = col;
			this.alphabet = alphabet;
			
		}
	}
	static int n,m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			char[][] map = new char[n][m];
			for(int i=0;i<n;i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			Deque<Item> q = new ArrayDeque<>();
			StringBuilder ans = new StringBuilder();
			boolean[][] check = new boolean[n][m];
			
			q.add(new Item(0, 0, map[0][0]));
			
			char minAlphabet = map[0][0];
			while(!q.isEmpty()) {
				boolean flag = false;
				ans.append(minAlphabet);
				
				int size = q.size();
				char minNextAlphabet = 'z';
				for(int i=0;i<size;i++) {
					Item front = q.poll();
					if(front.row == n-1 && front.col == m-1) {
						flag = true;
						break;
					}
					if(front.alphabet == minAlphabet) {
						int nRow1 = front.row + delta[0][0];
						int nCol1 = front.col + delta[0][1];
						int nRow2 = front.row + delta[1][0];
						int nCol2 = front.col + delta[1][1];
						
						if(!isOut(nRow1, nCol1) && !isOut(nRow2, nCol2)) {
							if(map[nRow1][nCol1] == map[nRow2][nCol2]) {
								if(minNextAlphabet >= map[nRow1][nCol1] && !check[nRow1][nCol1]) {
									minNextAlphabet = map[nRow1][nCol1];
									check[nRow1][nCol1] = true;
									q.add(new Item(nRow1, nCol1, map[nRow1][nCol1]));
								}
								if(minNextAlphabet >= map[nRow2][nCol2] && !check[nRow2][nCol2]) {
									minNextAlphabet = map[nRow2][nCol2];
									check[nRow2][nCol2] = true;
									q.add(new Item(nRow2, nCol2, map[nRow2][nCol2]));
								}
							}else if(map[nRow1][nCol1] >= map[nRow2][nCol2]) {
								if(minNextAlphabet >= map[nRow2][nCol2] && !check[nRow2][nCol2]) {
									minNextAlphabet = map[nRow2][nCol2];
									check[nRow2][nCol2] = true;
									q.add(new Item(nRow2, nCol2, map[nRow2][nCol2]));
								}
							}else {
								if(minNextAlphabet >= map[nRow1][nCol1] && !check[nRow1][nCol1]) {
									minNextAlphabet = map[nRow1][nCol1];
									check[nRow1][nCol1] = true;
									q.add(new Item(nRow1, nCol1, map[nRow1][nCol1]));
								}
							}
						}else if(!isOut(nRow1, nCol1) && isOut(nRow2, nCol2)) {
							if(minNextAlphabet >= map[nRow1][nCol1] && !check[nRow1][nCol1]) {
								minNextAlphabet = map[nRow1][nCol1];
								check[nRow1][nCol1] = true;
								q.add(new Item(nRow1, nCol1, map[nRow1][nCol1]));
							}
						}else if(isOut(nRow1, nCol1) && !isOut(nRow2, nCol2)) {
							if(minNextAlphabet >= map[nRow2][nCol2] && !check[nRow2][nCol2]) {
								minNextAlphabet = map[nRow2][nCol2];
								check[nRow2][nCol2] = true;
								q.add(new Item(nRow2, nCol2, map[nRow2][nCol2]));
							}
						}
					}
				}
				minAlphabet = minNextAlphabet;
				if(flag) break;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static boolean isOut(int row, int col) {
		return !(0<=row && row<n && 0<=col && col<m); 
	}
}
