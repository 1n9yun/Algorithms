package ps;

import java.util.Scanner;

public class boj14939_lightoff {
	static int[][] delta = {{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	static int ans = 10001;
	static char[][] map;
	static char[][] temp;
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = 10;
		
		map = new char[n][n];
		temp = new char[n][n];
		
		for(int i=0;i<n;i++) {
			map[i] = sc.next().toCharArray();
		}
		
		_2PI10(0, new int[n]);
		
		System.out.println(ans == 10001 ? -1 : ans);
	}
	
	static void _2PI10(int idx, int[] result) {
		if(idx == result.length) {
			copy(map, temp);
			int tAns = 0;
			
			for(int i=0;i<result.length;i++) {
				if(result[i] == 1) {
					press(temp, 0, i);
					tAns++;
				}
			}
			
			for(int i=1;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(temp[i-1][j] == 'O') {
						press(temp, i, j);
						tAns++;
					}
				}
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(temp[i][j] != '#') return;
				}
			}
			
			ans = Math.min(ans, tAns);
			return;
		}
		
		for(int i=0;i<2;i++) {
			result[idx] = i;
			_2PI10(idx + 1, result);
		}
	}
	static void copy(char[][] original, char[][] target) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				target[i][j] = original[i][j];
			}
		}
	}
	static void press(char[][] map, int r, int c) {
		for(int dir=0;dir<delta.length;dir++) {
			int nRow = r + delta[dir][0];
			int nCol = c + delta[dir][1];
			
			if(0<=nRow && nRow<n && 0<=nCol && nCol<n) {
				map[nRow][nCol] = map[nRow][nCol] == 'O' ? '#' : 'O';
			}
		}
	}
}
