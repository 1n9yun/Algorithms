package ps.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj11559_puyopuyo {
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
		
		int r = 12;
		int c = 6;
		char[][] field = new char[r][c];
		boolean[][] check = new boolean[r][c];
		for(int i=0;i<r;i++) {
			char[] input = sc.next().toCharArray();
			for (int j=0;j<c;j++) {
				field[i][j] = input[j];
			}
		}
		
		int chain = 0;
		Queue<Pair> q = new LinkedList<>();
		boolean more = true;
		while(more) {
			more = false;
			for(int i=r-1;i>=0;i--) {
				for(int j=c-1;j>=0;j--) {
					if(!check[i][j] && field[i][j] != '.') {
						check[i][j] = true;
						ArrayList<Pair> subField = new ArrayList<>();
						char color = field[i][j];
						
						Pair start = new Pair(i,j);
						q.add(start);
						subField.add(start);
						
						while(!q.isEmpty()) {
							Pair front = q.poll();
							for(int dir=0;dir<4;dir++) {
								int nRow = front.first + delta[dir][0];
								int nCol = front.second + delta[dir][1];
								
								if(0<=nRow && nRow<r && 0<=nCol && nCol<c && field[nRow][nCol] == color && !check[nRow][nCol]) {
									check[nRow][nCol] = true;
									Pair next = new Pair(nRow, nCol);
									q.add(next);
									subField.add(next);
								}
							}
						}
						if(subField.size() > 3) {
							more = true;
							for(Pair p : subField) {
								field[p.first][p.second] = '.';
							}
						}
					}
				}
			}
			if(!more) break;
			
//			이제 끌어내리기
			for(int j=c-1;j>=0;j--) {
				int bottom = r - 1;
				while(bottom >= 0 && field[bottom][j] != '.') bottom--;
				if(bottom < 0) continue;
				
				int rowIdx = bottom;
				while(rowIdx >= 0 && bottom >= 0) {
					while(rowIdx >= 0 && field[rowIdx][j] == '.') rowIdx--;
					if(rowIdx < 0) break;
					
//					swap
					char temp = field[bottom][j];
					field[bottom][j] = field[rowIdx][j];
					field[rowIdx][j] = temp;
					bottom--;
				}
			}
			chain++;
			for(boolean[] sub : check) Arrays.fill(sub, false);
		}
		System.out.println(chain);
	}
	
	static void print(char[][] field) {
		for(int i=0;i<field.length;i++) {
			for(int j=0;j<field[0].length;j++) {
				System.out.print(field[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
