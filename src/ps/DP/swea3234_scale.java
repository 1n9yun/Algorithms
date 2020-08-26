package ps.DP;

import java.util.Scanner;

public class swea3234_scale {
	static int n;
	static int[][] dp;
	static int[] weights;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			
			weights = new int[n];
			dp = new int[1<<n][1<<n];
			
			for(int i=0;i<n;i++) {
				weights[i] = sc.nextInt();
			}
			
			System.out.println("#" + tc + " " + back(0, 0, 0, 0));
		}
	}
	
//	오른쪽이 작거나 같아야됨
	static int back(int left, int right, int lW, int rW) {
//		System.out.print(lW + " " + rW + " ");
//		System.out.println(Integer.toBinaryString(left) + " " + Integer.toBinaryString(right));
		if((left | right) == (1<<n) - 1) return dp[left][right] = 1;
		if(dp[left][right] != 0) return dp[left][right];
		
		
		int res = 0;
		for(int i=0;i<n;i++) {
			if((left & (1<<i)) == 0 && (right & (1<<i)) == 0) {
//				오른쪽에 놓기
				if(lW >= rW + weights[i]) {
					res += back(left, right | (1<<i), lW, rW + weights[i]);
				}
//				왼쪽에 놓기
				res += back(left | (1<<i), right, lW + weights[i], rW);
			}
		}
		
		return dp[left][right] = res;
	}
}
