package ps.DFS;

import java.util.Arrays;
import java.util.Scanner;

public class swea5684_exercise {
	static int ans;
	static int[][] adjMat = new int[401][401];
	static boolean[] check;
	static int n, m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			for(int[] sub : adjMat) Arrays.fill(sub, 0);
			for(int i=0;i<m;i++) {
				int s = sc.nextInt();
				int e = sc.nextInt();
				int c = sc.nextInt();
				
				adjMat[s][e] = c;
			}
			
			ans = 987654321;
			
			for(int i=1;i<=n;i++) {
				check = new boolean[n+1];
				check[i] = true;
				dfs(i, i, 0);
			}
			
			System.out.println("#" + tc + " " + (ans == 987654321 ? -1 : ans));
		}
	}
	static void dfs(int start, int from, int count) {
		if(count >= ans) return;
		if(adjMat[from][start] != 0) {
			ans = Math.min(ans, count + adjMat[from][start]);
			return;
		}
		for(int i=1;i<=n;i++) {
			if(adjMat[from][i] != 0 && !check[i]) {
				check[i] = true;
				dfs(start, i, count + adjMat[from][i]);
			}
		}
	}
}
