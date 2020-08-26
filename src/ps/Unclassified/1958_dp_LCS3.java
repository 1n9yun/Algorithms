import java.util.Scanner;

public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		char[] str1 = sc.next().toCharArray();
		char[] str2 = sc.next().toCharArray();
		char[] str3 = sc.next().toCharArray();

		int[][][] dp = new int[str1.length + 1][str2.length + 1][str3.length + 1];		
		int ans = -1;

		for(int i=0;i<str1.length;i++){
			for(int j=0;j<str2.length; j++){
				for(int k=0;k<str3.length;k++){
					if(str1[i] == str2[j] && str2[j] == str3[k]){
						dp[i+1][j+1][k+1] = dp[i][j][k] + 1;
						ans = dp[i+1][j+1][k+1] > ans ? dp[i+1][j+1][k+1] : ans;
					}
					else{
						dp[i+1][j+1][k+1] = Math.max(threeMax(dp[i][j+1][k+1], dp[i+1][j][k+1], dp[i+1][j+1][k])
													, threeMax(dp[i][j][k+1], dp[i][j+1][k], dp[i+1][j][k]));
					}
				}
			}
		}

		System.out.println(ans);
	}
	public static int threeMax(int n1, int n2, int n3){
		return Math.max(n1, Math.max(n2, n3));
	}
}