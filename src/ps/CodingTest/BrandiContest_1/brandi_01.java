package ps.CodingTest.BrandiContest_1;

import java.util.Scanner;



public class brandi_01 {
	static int[][] arrange = {
			{90,200},{80,90},{75,80},{68,75},{60,68},{0,60}
		};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int maxBeat = 220 - n;
		int[] ans = new int[arrange.length];
		
		while(sc.hasNextInt()) {
			double beat = sc.nextInt();
			double percentage = (beat / maxBeat) * 100;
			
			System.out.println(percentage);
			for(int i=0;i<arrange.length;i++) {
				if(arrange[i][0] <= percentage && percentage < arrange[i][1]) {
					ans[i]++;
					break;
				}
			}
		}
		
		for(int i=0;i<ans.length;i++) {
			System.out.print(ans[i] + " ");
		}
	}
}
