package ps.naver2020_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(solution(sc.next(), sc.next()));
	}
	public static String solution(String m, String k) {
		char[] charSeq = m.toCharArray();
		
		List<Integer>[] indexes = new ArrayList[26];
		for(int i=0;i<26;i++) indexes[i] = new ArrayList<>();
		
		for(int i=0;i<m.length();i++) {
			char c = m.charAt(i);
			indexes[c - 'a'].add(i);
		}
		
		int prevIdx = -1;
		
		for(int i=0;i<k.length();i++) {
			char c = k.charAt(i);
			
			for(int j=0;j<indexes[c-'a'].size();j++) {
				int idx = indexes[c - 'a'].get(j);
				if(idx > prevIdx) {
					prevIdx = idx;
					charSeq[idx] = ' ';
					break;
				}
			}
		}
		
		StringBuilder answer = new StringBuilder();
		for(int i=0;i<charSeq.length;i++) {
			if(charSeq[i] == ' ') continue;
			answer.append(charSeq[i]);
		}
		
		return answer.toString();
	}
}
