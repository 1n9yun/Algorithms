package ps.KMP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1305_advertisement {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		
		System.out.println(n - getPi(str)[n-1]);
	}
	
	static int[] getPi(String pattern) {
		int[] pi = new int[pattern.length()];
		char[] p = pattern.toCharArray();
		
		for(int i=1,j=0;i<p.length;i++) {
			while(j>0 && p[i] != p[j]) j = pi[j-1];
			if(p[i] == p[j]) pi[i] = ++j;
		}
		
		return pi;
	}
}
