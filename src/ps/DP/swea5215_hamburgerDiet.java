package ps.DP;

import java.util.Scanner;

public class swea5215_hamburgerDiet {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int l = sc.nextInt();
			
			int[][] dp = new int[l+1][n+1];
			int[][] data = new int[n+1][2];
			
			for(int i=1;i<=n;i++) {
				data[i][0] = sc.nextInt();
				data[i][1] = sc.nextInt();
			}
			
//			0점수 1칼로리
			
			for(int j=1;j<=n;j++) {
				for(int i=1;i<=l;i++) {
					if(i >= data[j][1]) {
						dp[i][j] = Math.max(dp[i][j-1], dp[i-data[j][1]][j-1] + data[j][0]);
					}else dp[i][j] = dp[i][j-1];
				}
			}
			
			System.out.println("#" + tc + " " + dp[l][n]);
		}
	}
}
