package ps.Combination;

import java.util.Arrays;
import java.util.Scanner;

public class boj1062_teaching {
	static boolean[] learned;
	static int n, k;
	static String[] input;
	static int ans = 0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		k = sc.nextInt();
		
		if(k < 5) {
			System.out.println(0);
			return;
		}
		learned = new boolean[26];
		input = new String[n];
		k-=5;
		learned['a' - 'a'] = true;
		learned['n' - 'a'] = true;
		learned['t' - 'a'] = true;
		learned['i' - 'a'] = true;
		learned['c' - 'a'] = true;
		
		for(int i=0;i<n;i++) {
			String temp = sc.next();
			input[i] = temp.substring(4, temp.length() - 4);
		}
		
		comb(0, k);
		
		System.out.println(ans);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == 0) {
			int res = 0;
			for(int i=0;i<n;i++) {
				boolean flag = true;
				for(int j=0;j<input[i].length();j++) {
					if(!learned[input[i].charAt(j) - 'a']) {
						flag = false;
						break;
					}
				}
				if(flag) res++;
			}
			
			ans = Math.max(ans, res);
			return;
		}
		
		for(int i=idx;i<learned.length;i++) {
			if(learned[i]) continue;
			
			learned[i] = true;
			comb(i, cnt - 1);
			learned[i] = false;
		}
	}
}
