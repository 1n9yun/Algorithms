package ps.KMP;

import java.util.Scanner;

public class boj16916_subString {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String target = sc.next();
		String pattern = sc.next();
		
		System.out.println(isMatched(target, pattern) ? 1 : 0);
	}
	
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		
		for(int i=1,j=0;i<pattern.length();i++) {
			while(j>0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			if(pattern.charAt(i) == pattern.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	
	static boolean isMatched(String target, String pattern) {
		int[] pi = getPi(pattern);
		
		for(int i=0,j=0;i<target.length();i++) {
			while(j>0 && target.charAt(i) != pattern.charAt(j)) {
				j = pi[j-1];
			}
			if(target.charAt(i) == pattern.charAt(j)) {
				if(j == pattern.length() - 1) return true;
				else j++;
			}
		}
		return false;
	}
}
