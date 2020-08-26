package ps.Brute_Force;

import java.util.Scanner;

public class swea10505_unBalancedIncome {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		int[] arr = new int[10000];
		
		for(int tc=1;tc<=TC;tc++) {
			int n = sc.nextInt();
			int ans = 0;
			
			double avg = 0;
			for(int i=0;i<n;i++) {
				arr[i] = sc.nextInt();
				avg += arr[i];
			}
			avg /= n;
			
			for(int i=0;i<n;i++) {
				if(avg >= arr[i]) ans++;
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
}
