package ps.BFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bj3055_flood {
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static class Pair<U, V>{
		U first;
		V second;

		public Pair(U first, V second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		char[][] forest = new char[r][c];
		int[][] flood = new int[r][c];
		boolean[][] check = new boolean[r][c];
		
		Pair<Integer, Integer> hh = null;
		Pair<Integer, Integer> dest = null;
		Queue<Pair<Pair<Integer, Integer>, Integer>> q = new LinkedList<>();
		for(int i=0;i<r;i++) {
			char[] input = sc.next().toCharArray();
			for(int j=0;j<c;j++) {
				flood[i][j] = 987654321;
				forest[i][j] = input[j];
				if(input[j] == 'S') {
					hh = new Pair<>(i, j);
					forest[i][j] = '.';
				}
				else if(input[j] == 'D') {
					dest = new Pair<>(i, j);
				}
				else if(input[j] == '*') {
					check[i][j] = true;
					q.add(new Pair<>(new Pair<>(i,j), 0));
					flood[i][j] = 0;
				}
				else if(input[j] == 'X') {
					flood[i][j] = -1;
				}
			}
		}
		while(!q.isEmpty()) {
			Pair<Pair<Integer,Integer>, Integer> front = q.poll();
			
			for(int dir=0;dir<4;dir++) {
				int nRow = front.first.first + delta[dir][0];
				int nCol = front.first.second + delta[dir][1];
				
				if(0<=nRow && nRow<r && 0<=nCol && nCol<c && forest[nRow][nCol] == '.' && !check[nRow][nCol]) {
					check[nRow][nCol] = true;
					flood[nRow][nCol] = front.second + 1;
					q.add(new Pair<>(new Pair<>(nRow, nCol), front.second+1));
				}
			}
		}
		
		check = new boolean[r][c];
		check[hh.first][hh.second] = true;
		q.add(new Pair<>(hh, 0));
		
		int ans = -1;
		while(!q.isEmpty()) {
			Pair<Pair<Integer,Integer>, Integer> front = q.poll();
			if(front.first.first == dest.first && front.first.second == dest.second) {
				ans = front.second;
				break;
			}
			for(int dir=0;dir<4;dir++) {
				int nRow = front.first.first + delta[dir][0];
				int nCol = front.first.second + delta[dir][1];
				
				if(0<=nRow && nRow<r && 0<=nCol && nCol<c && !check[nRow][nCol] && front.second + 1 < flood[nRow][nCol]) {
					check[nRow][nCol] = true;
					q.add(new Pair<>(new Pair<>(nRow, nCol), front.second + 1));
				}
			}
		}
		
		System.out.println(ans == -1 ? "KAKTUS" : ans);
	}
}
