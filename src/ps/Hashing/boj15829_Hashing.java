package ps.Hashing;

import java.util.Scanner;

public class boj15829_Hashing {
	static final int MOD = 1234567891;
	static final int R = 31;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int l = sc.nextInt();
		
		String str = sc.next();
		
		long ans = 0;
		for(int i=0;i<l;i++) {
			long idx = (str.charAt(i) - 'a') + 1;
			
			ans = (ans + (idx * power(R, i)) % MOD) % MOD;
		}
		
		System.out.println(ans);
	}
	
	static long power(long n, int power) {
		if(power == 0) return 1;
		if(power == 1) return n;
		
		if(power % 2 == 0) {
			return power(n, power / 2) * power(n, power / 2) % MOD;
		}else {
			return power(n, power / 2) * power(n, power / 2) % MOD * n % MOD;
		}
	}
}
