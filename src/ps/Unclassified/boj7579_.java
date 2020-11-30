package ps.Unclassified;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj7579_{
	public static void main(String[] args) throws Exception{
		// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int apps[][] = new int[2][n];
		int dp[][] = new int[n][10001];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++){
			apps[0][i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++){
			apps[1][i] = Integer.parseInt(st.nextToken());
		}

		dp[0][apps[1][0]] = apps[0][0];
		int ans = 987654321;
		for(int i=1;i<n;i++){
			//	for(int j=10000;j>=app[1][i];j--){
			for(int j=0;j<10001;j++){
				if(j - apps[1][i] >= 0)
					dp[i][j] = dp[i-1][j-apps[1][i]] + apps[0][i];
				//	여기서 dp[i-1][j]의 의미는 i-1번 째에 비활성하지 않고 넘어온 것
				dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
				if(dp[i][j] >= m) ans = ans > j ? j : ans;
			}
		}

		System.out.println(ans);
	}
}