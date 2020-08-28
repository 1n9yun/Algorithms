package ps;

import java.util.Scanner;

public class boj2805_나무자르기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int ans = 0;
		int left = 1;
		int right = -1;
		
		int[] trees = new int[n];
		for(int i=0;i<n;i++) {
			trees[i] = sc.nextInt();
			right = Math.max(right, trees[i]);
		}
		
		right--;
		while(left <= right) {
			int mid = (left + right) / 2;
			long cnt = 0;
			for(int i=0;i<n;i++) {
				int diff = trees[i] - mid;
				if(diff > 0) {
					cnt += diff;
				}
			}
			
			if(cnt < m) {
				right = mid - 1;
			}else {
				left = mid + 1;
				ans = mid;
			}
		}
		
		System.out.println(ans);
	}
}

// 또분탐색
// long!!