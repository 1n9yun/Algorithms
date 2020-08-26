package ps.DFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea5643_heightOrder {
	
	static int n,m;
	static int[][] adjMat;
	static int[][] res;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			
			m = sc.nextInt();
			adjMat = new int[n+1][n+1];
			res = new int[n+1][2];
			
			for(int i=0;i<m;i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				adjMat[a][b] = 1;
			}
			
			for(int i=1;i<=n;i++) {
				boolean[] check = new boolean[n+1];
				check[i] = true;
				res[i][1] = solve(i, check);
			}
			
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					if(adjMat[i][j] == 1) {
						adjMat[i][j] = 0;
						adjMat[j][i] = 2;
					}
				}
			}
			
			for(int i=1;i<=n;i++) {
				boolean[] check = new boolean[n+1];
				check[i] = true;
				res[i][0] = solve(i, check);
			}
			
			int ans = 0;
			for(int i=1;i<=n;i++) {
				if(res[i][0] + res[i][1] == n - 1) ans++;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int solve(int v, boolean[] check) {
		int cnt = 0;
		
		for(int i=1;i<=n;i++) {
			if(adjMat[v][i] != 0 && !check[i]) {
				check[i] = true;
				cnt += solve(i, check) + 1;
			}
		}
		
		return cnt;
	}
}
