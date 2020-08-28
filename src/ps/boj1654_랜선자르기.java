package ps;

import java.util.Scanner;

public class boj1654_랜선자르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int k = sc.nextInt();
		int n = sc.nextInt();
		int left = 1;
		int right = (1<<31)-1;
		
		int[] lines = new int[k];
		for(int i=0;i<k;i++) {
			lines[i] = sc.nextInt();
		}
		
		int ans = -1;
		while(left <= right) {
			int mid = (left + right) / 2;
			int cnt = 0;
			
			for(int i=0;i<k;i++) {
				cnt += lines[i] / mid;
			}
			if(cnt < n) {
				right = mid - 1;
			}else {
				left = mid + 1;
				ans = Math.max(ans, mid);
			}
		}
		
		System.out.println(ans);
	}
}
