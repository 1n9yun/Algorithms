package ps.Greedy;

import java.util.Arrays;
import java.util.Scanner;

public class swea6719_programming_lect {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int tc=1;tc<=TC;tc++) {
			double ans = 0;
			
			int n = sc.nextInt();
			int k = sc.nextInt();
			
			int[] input = new int[n];
			for(int i=0;i<n;i++) {
				input[i] = sc.nextInt();
			}
			
			Arrays.sort(input);
			
			for(int i=n-k;i<n;i++) {
				ans = (ans + (double)input[i]) / 2.000000;
			}
			
			System.out.printf("#%d %.6f\n", tc, ans);
		}
	}
}
