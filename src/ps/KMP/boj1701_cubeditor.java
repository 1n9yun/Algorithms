package ps.KMP;

import java.util.Scanner;

public class boj1701_cubeditor {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		
		int ans = -1;
		for(int i=0;i<input.length();i++) {
			ans = Math.max(ans, getMaxFail(input.substring(i, input.length())));
		}
		
		System.out.println(ans);
	}
	
	static int getMaxFail(String pattern) {
		int[] res = new int[pattern.length()];
		int ret = 0;
		int j = 0;
		for(int i=1;i<pattern.length();i++) {
			while(j>0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = res[j-1];
			}
			if(pattern.charAt(i) == pattern.charAt(j)) {
				res[i] = ++j;
				ret = Math.max(ret, res[i]);
			}
		}
		
		return ret;
	}
}
