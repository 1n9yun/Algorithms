package ps.Permutation;

import java.util.Scanner;

public class swea7208_colorMap {
	static int n;
	static boolean[][] adjMat;
	static int[] colors;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			adjMat = new boolean[n][n];
			colors = new int[n];
			ans = 987654321;
			
			for(int i=0;i<n;i++) {
				colors[i] = sc.nextInt();
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					adjMat[i][j] = sc.nextInt() == 1 ? true : false;
				}
			}
			
			perm(0, new int[n]);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void perm(int idx, int[] result) {
		if(idx == result.length) {
			for(int i=0;i<n;i++) {
				boolean[] check = new boolean[n];
				check[i] = true;
				if(!dfs(i, result, check)) return;
			}
			int cnt = 0;
			for(int i=0;i<result.length;i++) {
				if(result[i] != colors[i]) cnt++;
			}
			
			ans = Math.min(ans, cnt);
			
			return;
		}
		
		for(int i=1;i<=4;i++) {
			result[idx] = i;
			perm(idx + 1, result);
		}
	}
	
	static boolean dfs(int v, int[] result, boolean[] check) {
		for(int i=0;i<n;i++) {
			if(adjMat[v][i] && !check[i]) {
				check[i] = true;
				if(result[i] == result[v]) return false;
				else if(!dfs(i, result, check)) return false;
			}
		}
		return true;
	}
}
