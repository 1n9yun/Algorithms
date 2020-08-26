package ps.Permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class boj15683_watch {
	static class Pair{
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
	}
	static class CCTV{
		Pair pos;
		int kind;
		
		public CCTV(Pair pos, int kind) {
			super();
			this.pos = pos;
			this.kind = kind;
		}
	}
	static int[][] delta = {{-1,0},{0,1},{1,0},{0,-1}};
	static int[][] cctvDir = {{}, {0}, {0,2},{0,1},{0,1,2},{0,1,2,3}};
	static ArrayList<CCTV> cctvs;
	static int n,m;
	static int[][] map;
	static int[][] temp;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		map = new int[n][m];
		temp = new int[n][m];
		cctvs = new ArrayList<>();
		
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++) {
				map[i][j] = sc.nextInt();
				
				if(1 <= map[i][j] && map[i][j] <= 5) {
					cctvs.add(new CCTV(new Pair(i, j), map[i][j]));
				}
			}
		}
		
		ans = 8 * 8 + 1;
		nPIr(0, new int[cctvs.size()]);
		
		System.out.println(ans);
	}
	
	static void nPIr(int idx, int[] result) {
		if(idx == result.length) {
			for(int i=0;i<n;i++) {
				temp[i] = map[i].clone();
			}
			
			for(int i=0;i<result.length;i++) {
				CCTV c = cctvs.get(i);
				
				for(int dIdx=0;dIdx<cctvDir[c.kind].length;dIdx++) {
					int cDir = (cctvDir[c.kind][dIdx] + result[i]) % 4;
					
					int row = c.pos.first + delta[cDir][0];
					int col = c.pos.second + delta[cDir][1];
					
					while(0<= row && row<n && 0<=col && col<m) {
						if(map[row][col] == 6) break;
						temp[row][col] = 7;
						
						row += delta[cDir][0];
						col += delta[cDir][1];
					}
				}
			}
			
			int blind = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(temp[i][j] == 0) blind++;
				}
			}
			
			ans = Math.min(ans, blind);
			return;
		}
		
		int kind = cctvs.get(idx).kind;
		int cnt = kind == 2 ? 2 : kind == 5 ? 1 : 4;
		
		for(int i=0;i<cnt;i++) {
			result[idx] = i;
			nPIr(idx + 1, result);
		}
	}
}

