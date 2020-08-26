package ps.Toss_200801;

import java.util.Scanner;

public class Toss_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		
		boolean ans = true;
		
		for(int i=0;i<input.length();i++) {
			char c = input.charAt(i);
			
			if(!(c == '1' || c == '2')) {
				ans = false;
				break;
			}
			
			if(c == '1') {
				boolean flag = false;
				for(int j=i+1;j<input.length();j++) {
					if(input.charAt(j) == '2') {
						flag = true;
						break;
					}
				}
				if(!flag) {
					ans = false;
					break;
				}
			}
		}
		
		System.out.println(ans);
	}
}
