package ps.Permutation;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class swea5656_brick_breaker {
	static class Item{
		int row, col, range;

		public Item(int row, int col, int range) {
			super();
			this.row = row;
			this.col = col;
			this.range = range;
		}
	}
	static int n,w,h;
	static int[][][] map;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<Item> q = new ArrayDeque<>();
	static int ans = 987654321;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			w = sc.nextInt();
			h = sc.nextInt();
			map = new int[2][h][w];
			ans = 987654321;
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					map[0][i][j] = sc.nextInt();
				}
			}
			
			int[] base = new int[w];
			for(int i=0;i<w;i++) base[i] = i;
			
			perm(0, base, new int[n]);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void perm(int idx, int[] base, int[] result) {
//		이거 때문에 시간 엄청 걸림 1151ms -> 341ms
//		솔직히 이건 아니지 않나..
		if(ans == 0) return;
		if(idx == n) {
//			solve
			for(int r=0;r<h;r++) {
				for(int c=0;c<w;c++) {
					map[1][r][c] = map[0][r][c];
				}
			}
			for(int i=1;i<=n;i++) {
				int shootPos = result[i - 1];
				for(int j=0;j<h;j++) {
					if(map[1][j][shootPos] != 0) {
						q.add(new Item(j, shootPos, map[1][j][shootPos]));
						map[1][j][shootPos] = 0;
						while(!q.isEmpty()) {
							Item front = q.poll();
							for(int range=1;range<front.range;range++) {
								for(int dir=0;dir<4;dir++) {
									int nRow = front.row + (delta[dir][0] * range);
									int nCol = front.col + (delta[dir][1] * range);
									
									if(0<=nRow && nRow<h && 0<=nCol && nCol<w && map[1][nRow][nCol] != 0) {
										if(map[1][nRow][nCol] != 1) q.add(new Item(nRow, nCol, map[1][nRow][nCol]));
										map[1][nRow][nCol] = 0;
									}
								}
							}
						}
						for(int c=0;c<w;c++) {
							int bottom = h-1;
							int target = h;
							while(bottom >= 0 && target >= 0) {
								while(bottom >= 0 && map[1][bottom][c] != 0) bottom--;
								if(target >= bottom) target = bottom - 1;
								while(target >= 0 && map[1][target][c] == 0) target--;
								if(target == -1 || bottom == -1) break;
								
								map[1][bottom][c] = map[1][target][c];
								map[1][target][c] = 0;
							}
						}
						break;
					}
				}
			}
			int cnt = 0;
			for(int r=0;r<h;r++) {
				for(int c=0;c<w;c++) {
					if(map[1][r][c] != 0) cnt++;
				}
			}
			ans = Math.min(ans, cnt);
			return;
		}
		
		for(int i=0;i<base.length;i++) {
			result[idx] = base[i];
			perm(idx + 1, base, result);
		}
	}
}
