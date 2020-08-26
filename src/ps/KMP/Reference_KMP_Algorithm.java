package ps.KMP;

import java.util.Arrays;

public class Reference_KMP_Algorithm {
//	fail (Pi) 함수는 첫 문자로 시작한 부분 문자열이 두 번 이상 반복될 때 그 길이를 저장하고 있음.
//	다시말해서 현재까지 매칭된 길이를 저장하고 있음
	
	static int[] fail;
//	static String text = "ABABABACABAABABACACA";
//	static String pattern = "ABABACA";
	static String text = "ABABABA";
	static String pattern = "ABA";
	
//	static String text = "abcabcabc";
//	static String pattern = "abbcbba";
	public static void main(String[] args) {
		fail = new int[pattern.length()];
		makeFail();
		
		System.out.println(Arrays.toString(fail));
		getMatched();
	}
	
	static void makeFail() {
		int j=0;
		for(int i=1;i<pattern.length();i++) {
			while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
				j = fail[j-1];
			}
			if(pattern.charAt(i) == pattern.charAt(j)) {
				fail[i] = ++j;
			}
		}
	}
	
	static void getMatched() {
		for(int i=0,j=0;i<text.length();i++) {
			while(j>0 && text.charAt(i) != pattern.charAt(j)) {
				System.out.println("Fail " + j + " to " + fail[j-1]);
				j = fail[j-1];
				System.out.println(i + " " + j);
			}
			if(text.charAt(i) == pattern.charAt(j)) {
				System.out.println(i + " " + j + " Matched");
				
				if(j == pattern.length()-1) {
					printMatched(i-j, i);
					System.out.println("Matched " + j + " to " + fail[j-1]);
					j = fail[j];
				}else j++;
			}
		}
	}
	
	static void printMatched(int start, int end) {
		for(int i=0;i<text.length();i++) {
			if(i == start) System.out.print('[');
			System.out.print(text.charAt(i));
			if(i == end) System.out.print(']');
			else System.out.print(" ");
		}
		System.out.println();
	}
}
