package ps.KMP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class boj1786_find {
	static ArrayList<Integer> ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String t = br.readLine();
		String p = br.readLine();
		
		ans = new ArrayList<>();
		
		countMatched(t, p);
		
		bw.write(ans.size() + "\n");
		for(int n : ans) bw.write(n + " ");
		
		bw.flush();
		bw.close();
	}
	
	static int[] getPi(String p) {
		int[] pi = new int[p.length()];
		for(int i=1, j=0;i<p.length();i++) {
			while(j>0 && p.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if(p.charAt(i) == p.charAt(j)) {
				pi[i] = ++j;
			}
		}
		return pi;
	}
	
	static void countMatched(String t, String p) {
		int[] pi = getPi(p);
		
		for(int i=0,j=0;i<t.length();i++) {
			while(j>0 && t.charAt(i) != p.charAt(j)) {
				j = pi[j-1];
			}
			if(t.charAt(i) == p.charAt(j)) {
				if(j == p.length() - 1) {
					ans.add(i - (p.length() - 1) + 1);
					j = pi[j];
				}else j++;
			}
		}
	}
}
