package ps;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class boj9376_jailbreak {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class Item{
		Pair pos;
		int door, broke;
		public Item(Pair pos, int door, int broke) {
			super();
			this.pos = pos;
			this.door = door;
			this.broke = broke;
		}
	}
	static int r, c;
	static char[][] map;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static Pair[] prisoner = new Pair[2];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			r = sc.nextInt();
			c = sc.nextInt();
			
			map = new char[r][c];
			
			int temp = 1;
			for(int i=0;i<r;i++) {
				map[i] = sc.next().toCharArray();
				for(int j=0;j<c;j++) {
					if(map[i][j] == '$') {
						prisoner[temp++] = new Pair(i, j);
					}
				}
			}
			
			int ans = 10001;
			
//			위, 아래 줄
			for(int i=1;i<c;i++) {
				if(map[0][i] == '.' || map[0][i] == '#') {
//					그 입구로 들어가자
					ans = Math.min(ans, bfs(map[0][i], new Pair(0, i)));
				}
				System.out.println();
				if(map[r-1][i] == '.' || map[r-1][i] == '#') {
					ans = Math.min(ans,  bfs(map[r-1][i], new Pair(r - 1, i)));
				}
				System.out.println();
			}
			
//			왼, 오른 줄
			for(int i=1;i<r;i++) {
				if(map[i][0] == '.' || map[i][0] == '#') {
					ans = Math.min(ans,  bfs(map[i][0], new Pair(i, 0)));
				}
				if(map[i][c-1] == '.' || map[i][c-1] == '#') {
					ans = Math.min(ans,  bfs(map[i][c-1], new Pair(i, c-1)));
				}
			}
			
			System.out.println(ans);
		}
	}
	
	static int bfs(char start, Pair p) {
		int[][][] check = new int[r][c][2];
		for(int[][] sub : check) {
			for(int[] subsub : sub) Arrays.fill(subsub, -1);
		}
		
//		0번 프리즈너 계산.
		
		return 0;
	}
}
