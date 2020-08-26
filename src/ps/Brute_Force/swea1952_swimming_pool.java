package ps.Brute_Force;

import java.util.Scanner;

public class swea1952_swimming_pool {
	static int[] prices;
	static int[] days;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		prices = new int[4];
		days = new int[12];
		for(int tc=1;tc<=TC;tc++) {
			for(int i=0;i<4;i++) prices[i] = sc.nextInt();
			for(int i=0;i<12;i++) days[i] = sc.nextInt();
			
			int ans = Math.min(prices[3], back(0));
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static int back(int idx) {
		if(idx >= 12) return 0;
		
		int ret = 987654321;
		
		ret = Math.min(ret, back(idx + 1) + days[idx] * prices[0]);
		ret = Math.min(ret, back(idx + 1) + prices[1]);
		return ret = Math.min(ret, back(idx + 3) + prices[2]);
	}
}
