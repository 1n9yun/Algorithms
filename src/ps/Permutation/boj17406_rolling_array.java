package ps.Permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class boj17406_rolling_array {
	static class Tuple{
		int first, second, third;

		public Tuple(int first, int second, int third) {
			super();
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}
	static int[][] delta = {{0,1},{1,0},{0,-1},{-1,0}};
	static int[][] getStart = {{-1,-1},{-1,1},{1,1},{1,-1}};
	static int[][] map;
	static int n, m, k;
	static Tuple[] rolls;
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		map = new int[n+1][m+1];
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=m;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		rolls = new Tuple[k];
		
		for(int i=0;i<k;i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			rolls[i] = new Tuple(r,c,s);
		}
		
		int[] base = new int[k];
		for(int i=0;i<k;i++) base[i] = i;
		
		perm(0, base, new int[k], new boolean[k]);
		
		System.out.println(ans);
	}
	
	static void perm(int idx, int[] base, int[] result, boolean[] check) {
		if(idx == result.length) {
			int[][] newMap = new int[n+1][m+1];
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=m;j++) {
					newMap[i][j] = map[i][j];
				}
			}
			
			for(int i=0;i<result.length;i++) {
				roll(rolls[result[i]], newMap);
			}
			ans = Math.min(ans, getMinOfRowSum(newMap));
			return;
		}
		for(int i=0;i<base.length;i++) {
			if(check[i]) continue;
			result[idx] = base[i];
			check[i] = true;
			perm(idx + 1, base, result, check);
			check[i] = false;
		}
	}
	
	static int getMinOfRowSum(int[][] newMap) {
		int res = 987654321;
		for(int i=1;i<=n;i++) {
			int sum = 0;
			for(int j=1;j<=m;j++) {
				sum += newMap[i][j];
			}
			res = Math.min(res, sum);
		}
		
		return res;
	}
	
	static void roll(Tuple roll, int[][] newMap) {
		int s = roll.third;
		while(s != 0) {
			int putNum = -1;
			for(int dir=0;dir<4;dir++) {
				int pRow = roll.first + (s * getStart[dir][0]);
				int pCol = roll.second + (s * getStart[dir][1]);
				int eRow = roll.first + (s * getStart[(dir+1)%4][0]);
				int eCol = roll.second + (s * getStart[(dir+1)%4][1]);
				
				if(putNum == -1)
					putNum = newMap[pRow][pCol];
				while(!(pRow == eRow && pCol == eCol)) {
					int temp = putNum;
					pRow += delta[dir][0];
					pCol += delta[dir][1];
					putNum = newMap[pRow][pCol];
					newMap[pRow][pCol] = temp;
				}
			}
			s--;
		}
	}
}
