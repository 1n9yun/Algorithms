package ps.Memoization;

import java.util.Arrays;
import java.util.Scanner;

public class swea9659_polynomial {
	static int n;
	static int[] t;
	static int[] a;
	static int[] b;
	static int m;
	static int[] x;
	static final int MOD = 998244353;
	static long[] memo;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			n = sc.nextInt();
			t = new int[n+1];
			a = new int[n+1];
			b = new int[n+1];
			
			for(int i=2;i<=n;i++) {
				t[i] = sc.nextInt();
				a[i] = sc.nextInt();
				b[i] = sc.nextInt();
			}
			m = sc.nextInt();
			x = new int[m];
			for(int i=0;i<m;i++) {
				x[i] = sc.nextInt();
			}
			
			System.out.print("#" + tc);
			for(int i=0;i<m;i++) {
				memo = new long[n+1];
				memo[0] = 1;
				memo[1] = x[i];
				System.out.print(" " + solve(n));
			}
			System.out.println();
		}
	}
	
	static long solve(int idx) {
		if(memo[idx] != 0) return memo[idx];
		
		switch(t[idx]) {
		case 1:
			return memo[idx] = (solve(a[idx]) + solve(b[idx])) % MOD;
		case 2:
			return memo[idx] = (a[idx] * solve(b[idx])) % MOD;
		case 3:
			return memo[idx] = (solve(a[idx]) * solve(b[idx])) % MOD;
		}
		
		return memo[idx];
	}
}
