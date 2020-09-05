package ps.CodingTest.BrandiContest_1;

import java.util.Arrays;
import java.util.Scanner;

public class brandi_03 {
	//	벨만 포드
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] adjMat = new int[n+1][n+1];
		for(int[] sub : adjMat) {
			Arrays.fill(sub, -101);
		}
		
		for(int i=0;i<m;i++) {
			adjMat[sc.nextInt()][sc.nextInt()] = sc.nextInt();
		}
		
		int[] ans = new int[n+1];
		Arrays.fill(ans, Integer.MAX_VALUE);
		ans[1] = 0;
		
		for(int _try=1;_try<=n;_try++) {
			for(int i=1;i<=n;i++) {
				if(ans[i] == Integer.MAX_VALUE) continue;
				for(int j=1;j<=n;j++) {
					if(adjMat[i][j] == -101) continue;
					ans[j] = Math.min(ans[j], ans[i] + adjMat[i][j]);
				}
			}
		}
		
		int[] cycleCheck = ans.clone();
		for(int i=1;i<=n;i++) {
			if(ans[i] == Integer.MAX_VALUE) continue;
			for(int j=1;j<=n;j++) {
				if(adjMat[i][j] == -101) continue;
				if(cycleCheck[j] > cycleCheck[i] + adjMat[i][j]) {
					System.out.println("bug");
					return;
				}
			}
		}
		
		System.out.println(ans[n] == Integer.MAX_VALUE ? "bug" : ans[n]);
	}
}
