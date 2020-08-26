package ps;

import java.util.Scanner;

public class boj1259_팰린드롬수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String input = sc.next();
		while(!input.equals("0")) {
			String ans = "yes";
			for(int i=0;i<input.length()/2;i++) {
				if(input.charAt(i) != input.charAt(input.length() - 1 - i)) {
					ans = "no";
					break;
				}
			}
			System.out.println(ans);
			
			input = sc.next();
		}
	}
}

// C++ 쓸 때 String을 배열처럼 접근하는거에 익숙해서 toCharArray를 썼었음
// 근데 오래걸리니까 charAt을 쓰자