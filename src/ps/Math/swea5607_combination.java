package ps.Math;

import java.util.Scanner;

public class swea5607_combination {
	final static int MOD = 1234567891;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		long[] factorials = new long[1000001];
		
		factorials[1] = 1;
		for(int i=2;i<factorials.length;i++) {
			factorials[i] = (factorials[i-1] * i) % MOD;
		}
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int r = sc.nextInt();
			int nr = n - r;
			
			long nMod = factorials[n];
			long rMod = factorials[r];
			long nrMod = factorials[n - r];
			
			System.out.println("#" + tc + " " + (((nMod * pow(rMod, MOD - 2)) % MOD) * pow(nrMod, MOD - 2) % MOD) % MOD);
		}
	}
	
	static long pow(long n, long p) {
		if(p == 0) return 1;
		if(p % 2 == 0) {
			long halfPowered = pow(n, p / 2);
			return (halfPowered * halfPowered) % MOD;
		}else {
			return (n * pow(n, p - 1)) % MOD;
		}
	}
}
