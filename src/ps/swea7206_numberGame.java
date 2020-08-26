package ps;

import java.util.Scanner;

public class swea7206_numberGame {
	static String n;
	static int[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		dp = new int[100000][1<<5];
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.next();
			
			System.out.println("#" + tc + " " + back(n, 0));
		}
	}
	
	static int back(String num, int slice) {
		if(num.length() < 2) return 0;
		if(dp[Integer.parseInt(num)][slice] != 0) return dp[Integer.parseInt(num)][slice];
		
		int res = 0;
		temp = 0;
		for(int i=1;i<num.length();i++) {
//			1군데 ~ len - 1군데 자르기 경우의 수, len은 1 ~ len-1 까지
			comb(num, 1, new int[i+1], new boolean[num.length()]);
			
			res = Math.max(res, temp);
		}
		return dp[Integer.parseInt(num)][slice] = res;
	}

	static int temp = 0;
	static void comb(String num, int cnt, int[] result, boolean[] check) {
		if(cnt == result.length) {
//			System.out.println(num + " " + Arrays.toString(result));
			int res = 1;
			int slice = 0;
			for(int i=1;i<result.length;i++) {
				slice |= result[i];
				res *= Integer.parseInt(num.substring(result[i-1], result[i]));
			}
			res *= Integer.parseInt(num.substring(result[result.length-1], num.length()));
			
//			System.out.println(res);
			temp = Math.max(temp, back(Integer.toString(res), slice) + 1);
			
			return;
		}
		
		for(int i=cnt;i<num.length();i++) {
			if(check[i]) continue;
			
			result[cnt] = i;
			check[i] = true;
			comb(num, cnt + 1, result, check);
		}
	}
}
