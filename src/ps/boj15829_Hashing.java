package ps;

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
			
			ans = (ans + (idx * Math.pow(R, i)))
		}
	}
}
