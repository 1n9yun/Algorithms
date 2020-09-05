package ps.CodingTest.KakaoBlind2020;

import java.util.Arrays;

public class programmers_괄호변환 {
	public static void main(String[] args) {
		char[] s = new char[99];
		Arrays.fill(s, 'a');
		
		System.out.println(solution(new String("()))((()")));
	}
	
	public static String solution(String p) {
		if(p.length() == 0 || isCorrect(p)) return p;
		
		int slice = getSliceIdx(p);
		
		return solve(p.substring(0, slice), p.substring(slice, p.length()));
	}
	public static String solve(String u, String v) {
		int slice = getSliceIdx(u);
		if(slice != -1 && slice != u.length()) u = solve(u.substring(0, slice), u.substring(slice, u.length()));
		
		if(isCorrect(u)) {
			return u + solution(v);
		}else {
			String temp = "(" + solution(v) + ")";
			
			for(int i=1;i<u.length()-1;i++) {
				temp += u.charAt(i) == '(' ? ')' : '(';
			}
			
			return temp;
		}
	}
	public static boolean isCorrect(String p) {
		int flag = 0;
		for(int i=0;i<p.length();i++) {
			if(p.charAt(i) == '(') flag++;
			else flag--;
			
			if(flag < 0) return false;
		}
		if(flag == 0) return true;
		else return false;
	}
	public static int getSliceIdx(String p) {
		for(int i=2;i<=p.length();i+=2) {
//			System.out.println(p.substring(0, i) + ", " + p.substring(i, p.length()));
			int open = 0;
			int close = 0;
			boolean pre = false;
			for(int j=0;j<i;j++) {
				switch(p.charAt(j)) {
				case '(':
					open++;
					break;
				case ')':
					close++;
					break;
				}
			}
			if(open == close) {
				if(i == p.length()) return i;
				pre = true;
			}
			if(pre) {
				open = 0;
				close = 0;
				for(int j=i;j<p.length();j++) {
					switch(p.charAt(j)) {
					case '(':
						open++;
						break;
					case ')':
						close++;
						break;
					}
				}
				if(open == close) {
					return i;
				}
			}
		}
		return -1;
	}
}
