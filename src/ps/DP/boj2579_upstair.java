package ps.DP;

import java.util.Scanner;

public class boj2579_upstair {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] points = new int[301];
		
		for(int i=1;i<=n;i++) {
			points[i] = sc.nextInt();
		}
		int[][] dp = new int[301][2];
		
		
		dp[1][0] = points[1];
		dp[2][0] = points[1] + points[2];
		dp[2][1] = points[2];
		
		for(int i=3;i<=n;i++) {
			dp[i][0] = dp[i-1][1] + points[i];
			dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + points[i];
		}
		
		System.out.println(Math.max(dp[n][0], dp[n][1]));
	}
}
