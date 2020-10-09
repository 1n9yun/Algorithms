package ps.CodingTest.BrandiContest_2;

import java.util.Scanner;

public class _01 {
	static char[][] cases = {{'R','G','B'}, {'R','B','G'},{'G','B','R'},{'G','R','B'},{'B','R','G'},{'B','G','R'}};
	static final int R = 0, G = 1, B = 2;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// r, g, b
		int[] prices = new int['S'];
		prices['R'] = sc.nextInt();
		prices['G'] = sc.nextInt();
		prices['B'] = sc.nextInt();
		
		char[] flowers = sc.next().toCharArray();
		
		int ans_price = 987654321;
		int ans_plant = 987654321;
		
		for(int i=0;i<cases.length;i++) {
			int tPrice = 0;
			int tPlant = 0;
			int[] items = new int['S'];
			
			for(int j=0;j<flowers.length;j++) {
				int case_idx = j % 3;
				if(flowers[j] == cases[i][case_idx]) continue;
				items[flowers[j]]++;
			}
			for(int j=0;j<flowers.length;j++) {
				int case_idx = j % 3;
				if(flowers[j] == cases[i][case_idx]) continue;
				
				tPlant++;
				if(items[cases[i][case_idx]] > 0) {
					items[cases[i][case_idx]]--;
				}else {
					tPrice += prices[cases[i][case_idx]];
				}
			}
			
			if(ans_price > tPrice) {
				ans_price = tPrice;
				ans_plant = tPlant;
			}else if(ans_price == tPrice) {
				if(ans_plant > tPlant) {
					ans_plant = tPlant;
				}
			}
		}
		
		System.out.println(ans_price + " " + ans_plant);
	}
}
