package ps;

import java.util.Scanner;

public class scpc2019q_updown {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] dp = new int[1000001];
		dp[2] = 1;
		
		for(int i=4;i<dp.length;i+=2) {
			dp[i] = dp[i/2] + 1;
			dp[i-1] = dp[i] + 1;
		}
		
		int[] pSum = new int[1000001];
		
		for(int i=1;i<pSum.length;i++) {
			dp[i] += dp[i-1];
		}
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			System.out.println("Case #" + tc);
			System.out.println(-dp[sc.nextInt() - 1] + dp[sc.nextInt()]);
		}
	}
}
