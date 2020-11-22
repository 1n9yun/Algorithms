package ps.Unclassified;

import java.util.Scanner;

// dp, 조합론
public class boj1256_사전{
	private static long dp[][] = new long[101][101];
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		long k = sc.nextInt();

		for(int i=0;i<=n;i++) dp[i][0] = 1;
		for(int i=0;i<=m;i++) dp[0][i] = 1;

		for(int i=1;i<=n;i++){
			dp[i][1] = dp[i-1][1] * (i+1) / i;
			dp[i][1] = dp[i][1] > 1000000000 ? 1000000001 : dp[i][1];
			for(int j=2;j<=m;j++){
				dp[i][j] = dp[i][j-1] * (j+i) / j;
				dp[i][j] = dp[i][j] > 1000000000 ? 1000000001 : dp[i][j];
			}
		}

		if(dp[n][m] < k) System.out.println(-1);
		else getAns(n,m,k);
	}

	public static void getAns(int n, int m, long k){
		if(n == 0){
			for(int i=0;i<m;i++){
				System.out.print('z');
			}
		}else if(m == 0){
			for(int i=0;i<n;i++){
				System.out.print('a');
			}
		}else{
			if(dp[n-1][m] >= k){
				System.out.print('a');
				getAns(n-1,m,k);
			}else{
				System.out.print('z');
				getAns(n,m-1,k - dp[n-1][m]);
			}
		}
	}
}