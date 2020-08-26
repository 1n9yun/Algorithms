package ps.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15486_quit2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] input = new int[n+1][2];
		
		for(int i=1;i<=n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n+1];
		for(int i=1;i<=n;i++) {
			int nDay = i + input[i][0] - 1;
			if(nDay <= n) {
				dp[nDay] = Math.max(dp[nDay], dp[i-1] + input[i][1]);
			}
			dp[i] = Math.max(dp[i], dp[i-1]);
		}
		
		System.out.println(dp[n]);
	}
}
