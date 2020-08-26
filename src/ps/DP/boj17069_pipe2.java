package ps.DP;

import java.util.Scanner;

public class boj17069_pipe2 {
	static long[][][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[][] map = new int[n+1][n+1]; 
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				map[i][j] = sc.nextInt();
			}
		}
		dp = new long[3][n+1][n+1];
//		가로 대각 세로
		
		dp[0][1][2] = 1;
		
		int tJ = 3;
		for(int i=1;i<=n;i++) {
			for(int j=tJ;j<=n;j++) {
				if(map[i][j] == 0) {
					dp[0][i][j] = dp[0][i][j-1] + dp[1][i][j-1];
					if(map[i-1][j] == 0 && map[i][j-1] == 0)
						dp[1][i][j] = dp[0][i-1][j-1] + dp[1][i-1][j-1] + dp[2][i-1][j-1];
					dp[2][i][j] = dp[1][i-1][j] + dp[2][i-1][j];
				}
			}
			tJ = 1;
		}
		System.out.println(dp[0][n][n] + dp[1][n][n] + dp[2][n][n]);
	}
}
