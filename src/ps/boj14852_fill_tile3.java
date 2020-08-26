package ps;

import java.util.Scanner;

public class boj14852_fill_tile3 {
	static final int MOD = 1000000007;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] dp = new int[n+1][2];
		
		dp[0][0] = 0;
		dp[0][1] = 1;
		dp[1][0] = 1;
		dp[1][1] = 2;
		
		for(int i=2;i<=n;i++) {
			
		}
	}
}
