package ps.Unclassified;

import java.util.Scanner;

public class Main{
	static int n,m;
	static int dp[][][][];
	static int chocolate[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for(int tc =1;tc<=TC;tc++){
			n = sc.nextInt();
			m = sc.nextInt();

			dp = new int[51][51][51][51];
			chocolate = new int[n+1][m+1];

			for(int i=1;i<=n;i++){
				for(int j=1;j<=m;j++){
					chocolate[i][j] = sc.nextInt() + chocolate[i-1][j] + chocolate[i][j-1] - chocolate[i-1][j-1];
				}
			}
			
			System.out.println("#" + tc + " " + back(1,1,n,m));
		}
	}
	public static int getPartialSum(int lRow, int lCol, int rRow, int rCol){
		return chocolate[rRow][rCol] - chocolate[rRow][lCol - 1] - chocolate[lRow - 1][rCol] + chocolate[lRow - 1][lCol - 1];
	}

	public static boolean first = true;
	public static int back(int lRow, int lCol, int rRow, int rCol){
		if(!first && lRow == 1 && lCol == 1 && rRow == n && rCol == m) return 0;
		else first = false;
		if(lRow > rRow || lCol > rCol) return 0;
		if(dp[lRow][lCol][rRow][rCol] != 0) return dp[lRow][lCol][rRow][rCol];

		int res = 987654321;
		int partialSum = getPartialSum(lRow, lCol, rRow, rCol);

		for(int i=1;i<=rRow-lRow;i++){
			res = Math.min(res, back(lRow, lCol, rRow-i, rCol) + back(rRow-i+1, lCol, rRow, rCol) + partialSum);
		}
		for(int j=1;j<=rCol-lCol;j++){
			res = Math.min(res, back(lRow, lCol, rRow, rCol-j) + back(lRow, rCol-j+1, rRow, rCol) + partialSum);
		}

		return dp[lRow][lCol][rRow][rCol] = res;
	}
}