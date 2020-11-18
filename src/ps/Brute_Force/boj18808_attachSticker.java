package ps.Brute_Force;

import java.util.Scanner;

public class boj18808_attachSticker {
	static int[][] map;
	static int[][][][] sticker;
	static int ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		
		map = new int[n][m];
		sticker = new int[k][4][][];
		
		for(int kc=0;kc<k;kc++) {
			sticker[kc][0] = new int[sc.nextInt()][sc.nextInt()];
			
			for(int i=0;i<sticker[kc][0].length;i++) {
				for(int j=0;j<sticker[kc][0][0].length;j++) {
					sticker[kc][0][i][j] = sc.nextInt();
				}
			}
			
			for(int rc=1;rc<4;rc++) {
				sticker[kc][rc] = rotatedSticker(sticker[kc][rc-1]);
			}
		}
		
		for(int kc=0;kc<k;kc++) {
			out:
			for(int rc=0;rc<4;rc++) {
				for(int i=0;i<n;i++) {
					for(int j=0;j<m;j++) {
						if(retrieveMap(i,j,kc,rc,0)){
							retrieveMap(i,j,kc,rc,1);
							break out;
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static int[][] rotatedSticker(int[][] pre){
		int[][] next = new int[pre[0].length][pre.length];
		
		for(int i=0;i<next.length;i++) {
			for(int j=0;j<next[0].length;j++) {
				next[i][j] = pre[pre.length - j - 1][i];
			}
		}
		
		return next;
	}
	
	static boolean retrieveMap(int si, int sj, int kc, int rc, int mode) {
		if(si + sticker[kc][rc].length > map.length || sj + sticker[kc][rc][0].length > map[0].length)
			return false;
		
		for(int i=si;i<si+sticker[kc][rc].length;i++) {
			for(int j=sj;j<sj+sticker[kc][rc][0].length;j++) {
				if(sticker[kc][rc][i-si][j-sj] == 0) continue;
				else if(map[i][j] == 1) return false;
				
				if(mode == 1) {
					map[i][j] = 1;
					ans++;
				}
			}
		}
		
		return true;
	}
}
