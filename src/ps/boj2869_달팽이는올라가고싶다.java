package ps;

import java.util.Scanner;

public class boj2869_달팽이는올라가고싶다 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int v = sc.nextInt();
		
		int ans = (v-b) / (a-b);
		if((v-b) % (a-b) != 0) ans++;
		
		System.out.println(ans);
	}
}
