package ps.Combination;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class boj1941_sevenPrincess{
	static char[][] map;
	static class Pair{
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static boolean[] check;
	static int ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map =  new char[5][5];
		check = new boolean[25];
		for(int i=0;i<5;i++) {
			char[] input = sc.next().toCharArray();
			for(int j=0;j<5;j++) {
				map[i][j] = input[j];
			}
		}
		
		int[] base = new int[25];
		for(int i=0;i<25;i++) base[i] = i;
		
		getCases(0, 0, new int[7], base);
		
		System.out.println(ans);
	}
	
	static void getCases(int idx, int cnt, int[] result, int[] base) {
		if(base.length - idx < result.length - cnt) return;
		if(cnt == result.length) {
			int sCnt = 0;
			for(int i=0;i<result.length;i++) {
				if(map[result[i]/5][result[i]%5] == 'S') sCnt++;
			}
			if(sCnt >= 4) {
//				연결 확인
				check[result[0]] = true;
				if(dfs(0, result) == 7) {
					ans++;
				}
				Arrays.fill(check, false);
			}
			return;
		}
		for(int i=idx;i<base.length;i++) {
			result[cnt] = i;
			getCases(i+1, cnt + 1, result, base);
		}
	}
	
	static int dfs(int idx, int[] result) {
		int res = 0;
		for(int dir=0;dir<4;dir++) {
			int nRow = result[idx]/5 + delta[dir][0];
			int nCol = result[idx]%5 + delta[dir][1];
			int next = nRow*5 + nCol;
			int rIdx = Arrays.binarySearch(result, next);
			
			if(0<=nRow && nRow<5 && 0<=nCol && nCol<5 && !check[next] && rIdx >= 0) {
				check[next] = true;
				res += dfs(rIdx, result);
			}
		}
		return res + 1;
	}
}