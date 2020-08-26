package ps.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class bj2651_car_race {
	static int maxDist;
	static int shopCnt;
	static int[] dists;
	static int[] repairTimes;
	static int[][][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		maxDist = sc.nextInt();
		shopCnt = sc.nextInt();
		dists = new int[shopCnt+2];
		repairTimes = new int[shopCnt+2];
		for(int i=1;i<=shopCnt+1;i++) {
			dists[i] = sc.nextInt();
		}
		for(int i=1;i<=shopCnt;i++) {
			repairTimes[i] = sc.nextInt();
		}
		
		dp = new int[shopCnt + 2][2][maxDist+1];
		for(int[][] arr : dp) for(int[] arr2 : arr) Arrays.fill(arr2, -1);
		
		System.out.println(back(1, 0, maxDist - dists[1]));
		
		ArrayList<Integer> ans = new ArrayList<>();
		
		int track = dp[1][0][maxDist - dists[1]];
		for(int i=2;i<=shopCnt+1;i++) {
			if(i == shopCnt+1) {
				if(track - repairTimes[i-1] == 0) ans.add(i-1);
			}
			else if(track - repairTimes[i-1] == dp[i][1][maxDist - dists[i]]) {
				ans.add(i-1);
				track = dp[i][1][maxDist - dists[i]];
			}
		}
		
		System.out.println(ans.size());
		for(int num : ans) System.out.print(num + " ");
	}
	static int back(int idx, int repaired, int remainDist) {
		if(idx == shopCnt + 1) return 0;
		if(dp[idx][repaired][remainDist] != -1) return dp[idx][repaired][remainDist];
		
		int ret = 987654321;
		if(remainDist - dists[idx + 1] >= 0) ret = Math.min(ret, back(idx + 1, 0, remainDist - dists[idx + 1]));
		
		return dp[idx][repaired][remainDist] = Math.min(ret, back(idx + 1, 1, maxDist - dists[idx + 1]) + repairTimes[idx]);
	}
}