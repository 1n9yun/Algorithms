package ps;

import java.util.Scanner;

public class boj2231_분해합 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for(int i=1;i<=1000000;i++) {
			int res = i;
			
			String itos = Integer.toString(i);
			for(int j=0;j<itos.length();j++) {
				res += (int) (itos.charAt(j) - '0');
			}
			
			if(res == n) {
				System.out.println(i);
				return;
			}
		}
		
		System.out.println(0);
	}
}

// 싹 다 돌려버리기